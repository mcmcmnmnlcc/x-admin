package com.jeeplus.modules.lottery.controller.kj;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.HttpUtil;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.lottery.dto.kj.PaOpenDataDto;
import com.jeeplus.modules.lottery.entity.kj.KjDataSet;
import com.jeeplus.modules.lottery.service.kj.KjDataSetService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/lottery/kj/kjDataSet")
public class KjDataSetController extends BaseController {
    @Autowired
    private KjDataSetService kjDataSetService;

    /**
     * 批量生成开奖数据
     * 提交生成
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result saveMass(@RequestBody PaOpenDataDto paOpenDataDto) {
        int statusCode=StatusCode.OK;
        String message="操作成功";

        try {
            String beginDate=paOpenDataDto.getBeginDate();
            String endDate=paOpenDataDto.getEndDate();
            //开始日期一定要大于等当天，开始日期不能在结束日期的后面
            if(DateUtils.pastDays(beginDate)>0||DateUtils.getDistanceOfTwoDate(beginDate,endDate)<0){
                throw new Exception("日期不合法");
            }
            int lockSeconds=Integer.parseInt(paOpenDataDto.getLockSeconds());
            String lotteryId=paOpenDataDto.getLotteryId();
            if("xglhc".equals(lotteryId)){
                try {
                    String rstring=lhcJiaoZhuDateQuartz();
                    Result result = new Result(true, StatusCode.OK, rstring);
                    return result;
                }catch (Exception e){
                    e.printStackTrace();
                    Result result = new Result(true, StatusCode.ERROR, "六合彩采集错误",e.getMessage());
                    return result;
                }


            }
            int dayNum=DateUtils.getDistanceOfTwoDate(beginDate,endDate);

            String lastLotteryQh=null;
            if("pk10".equals(lotteryId)||"bj28".equals(lotteryId)||"bjk3".equals(lotteryId)){
                String  lastDay  = DateUtils.getDayLater(beginDate,-1);
                lastLotteryQh=kjDataSetService.getPk10LotteryQh(lotteryId,lastDay);
                if(StringUtils.isBlank(lastLotteryQh)){
                    //lastLotteryQh="0";
                    message=beginDate+" 彩种："+lotteryId+"采集数据获取上一天的最大期号没有找到！";
                    Result result = new Result(true, StatusCode.ERROR, message);
                    return result;
                }
            }

            Map<String,Object> map=null;
            List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
            for (int i = 0; i <=dayNum ; i++) {
                map=new HashMap<String,Object>();
                String day=DateUtils.getDayLater(beginDate,i);
                String dayLaterOne=DateUtils.getDayLater(beginDate,i+1);
                String dayBeforeOne=DateUtils.getDayLater(beginDate,i-1);
                map.put("date",day);
                map.put("date+1",dayLaterOne);                //后一天的日期
                map.put("date-1",dayBeforeOne);               //前一天的日期
                map.put("dateFormat",day.replace("-",""));
                map.put("dateFormat+1",dayLaterOne.replace("-",""));
                map.put("lockSeconds",lockSeconds);
                map.put("lotteryId",lotteryId);
                //获取彩种当天的期数
                int count = kjDataSetService.getDayLastLotteryQh(lotteryId);
                if("pk10".equals(lotteryId)|| ("bj28".equals(lotteryId))){
                    map.put("reserved",Integer.parseInt(lastLotteryQh)+i* count);
                }
                if("bjk3".equals(lotteryId)){
                    map.put("reserved",Integer.parseInt(lastLotteryQh)+i*44);
                }
                list.add(map);
            }
            kjDataSetService.instBatchKjData(list);

        } catch (Exception e) {
            logger.error("批量生成彩种 异常",e.fillInStackTrace());
            message="操作失败";
            Result result = new Result(true, StatusCode.ERROR, message, e.getMessage());
            return result;
        }

        Result result = new Result(true, StatusCode.OK, message);
        return result;
    }

    /**
     * 获取六合彩jiao    @Scheduled(cron = "0 0 12 25-30 * ?" )
     * // <td class="td_kj">5</td></tr><tr class="td_date">
     // <option value="2017-08" selected style="background-color:#FFCC99">2017年08月</option>
     */
    public String lhcJiaoZhuDateQuartz() throws Exception {
        String rstring="六合彩完成采集";
        logger.info("六合彩定时任务         执行开始------------------------------------");

            //返回下个月的年月
            String date= DateUtils.getDateYM(1);
//			String date= "2017-08";
            KjDataSet kjEntity=kjDataSetService.getLhcLastEntity();
            if(date.equals(kjEntity.getKpDate().substring(0,7))){ // 如果是本月，则代表已经生成
                logger.info("六合彩定时任务         数据已经存在------------------------------------");
                rstring="数据已经存在";
                return rstring;
            }
            String resp= HttpUtil.doGet("http://www.kjrq.org/d/?d="+date,"utf-8");
            String regexDate="<option value=\"(\\d{4}-\\d{2})\" selected";
            Pattern pattern=Pattern.compile(regexDate);
            Matcher matcher=pattern.matcher(resp);
            String urlDate="";
            if(matcher.find()){
                urlDate=matcher.group(1);
            }
            if(!urlDate.equals(date)){
                logger.info("六合彩定时任务         月份不一致------------------------------------");
                rstring="月份不一致";
                return rstring;
            }

            String regex="<td class=\"td_kj[^\"]*\">(\\d{1,2})</td>";
            pattern=Pattern.compile(regex);
            matcher=pattern.matcher(resp);
            List<KjDataSet>  kjList=new ArrayList<KjDataSet>();
            String temp="";
            String year=date.substring(0,4);
            int lottery_qh=date.substring(5).equals("01")?Integer.parseInt(year+"000"):Integer.parseInt(kjEntity.getLotteryQh());
            Date kpDate=kjEntity.getPlanKjTime();
            String qh="";
            while(matcher.find()){
                temp=matcher.group(1);
                if(temp.length()<2){
                    temp="0"+temp;
                }
                KjDataSet kjData=new KjDataSet();
                kjData.setKpDate(date+"-"+temp);
                kjData.setLotteryId("xglhc");
                lottery_qh=lottery_qh+1;
                if(lottery_qh<10){
                    qh="00"+lottery_qh;
                }else if(lottery_qh<100&&lottery_qh>=10){
                    qh="0"+lottery_qh;
                }else{
                    qh=lottery_qh+"";
                }
                kjData.setLotteryQh(qh);
                kjData.setLockTime(DateUtils.parseDate(date+"-"+temp+" 21:25:00"));
                kjData.setPlanKjTime(DateUtils.parseDate(date+"-"+temp+" 21:30:00"));
                kjData.setQhKpTime(kpDate);
                kpDate=DateUtils.parseDate(date+"-"+temp+" 21:30:00");
                kjList.add(kjData);
            }

            for (int i = 0; i <kjList.size() ; i++) {
                System.out.println(ToStringBuilder.reflectionToString(kjList.get(i)));
            }
            kjDataSetService.instKjData(kjList);

        logger.info("六合彩定时任务         执行结束------------------------------------");
        return rstring;
    }

}
