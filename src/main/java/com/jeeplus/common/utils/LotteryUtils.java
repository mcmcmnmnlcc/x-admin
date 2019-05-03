package com.jeeplus.common.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Hello on 2017/4/14.
 */
public class LotteryUtils {

    private  static List<String>  strList=
            Arrays.asList(
                    new String[]{"0","1","2","3","4","5","6","7","8","9",
                    "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"});
    /**
     * 生成订单号
     * 规则：开头  1:安卓  2:苹果
     * @return
     */
    public static String  getOrderNum(String flag) throws ParseException {
        String order =flag+ DateUtils.dateFormat(new Date(),"MMddHHmmsss")+getStrFromList(5);
        return order;
    }

    /**
     * 从list里获取指定位数的字符串
     * @param num
     * @return
     */
    private static  String getStrFromList(int num){
        Collections.shuffle(strList);
        String str="";
        for (int i = 0; i<num; i++) {
            str+=strList.get(i);
        }
        return str;
    }

    /**
     * 把金额乘以 1000
     * @param value
     * @return
     */
    public static Long formatLongValue(String value){
        if(StringUtils.isBlank(value)){
            value="0";
        }
        BigDecimal bd=new BigDecimal(value).multiply(new BigDecimal(1000));
        return bd.longValue();
    }

    /**
     * 把千为单位的钱，转换为元,保留2位小数
     * @param value
     * @return
     */
    public static BigDecimal transforYuan(String value){
        if(StringUtils.isBlank(value)){
            return new BigDecimal(0);
        }
        //直接删除多余的位数
        BigDecimal bd=new BigDecimal(value).divide(new BigDecimal(1000)).setScale(2,BigDecimal.ROUND_DOWN);
        return bd;
    }


    /**
     * 把double数字转化为百分比
     * @param value
     * @return
     */
    public static String percentForDouble(Double value){
        NumberFormat nf = NumberFormat.getPercentInstance();
        //nf.setMinimumFractionDigits(2);//设置保留小数位
        //nf.setRoundingMode(RoundingMode.HALF_UP); //设置舍入模式
        String percent = nf.format(value);
        return percent;
    }


//    public static void main(String[] args) throws  Exception{
//        for (int i = 0; i <100 ; i++) {
//            System.out.println(getOrderNum("1"));
//
//        }
//
//
//    }

}
