package com.jeeplus.modules.lottery.entity.order;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class LotteryGenDanOrder extends NdataEntity {
    private  String pfmUserId;         //用户id

    private String orderNumber;		// 订单编号

    private String gdNumber;		// 跟单号码

    private Double commission;    //佣金

    private Double  backRate;   //回报率

    private Integer zhuihCountQs;  // 追号期数

    private String   zhuihStartQh ;  // 追号开始期号

    private String   zhuihAllQh ;  // 追号所有期号

    private  String  zhuihEndQh ; // 追号结束期号

    private  String  zhuihAllBs ; // 追号所有期号的倍数

    private  String  zhuihWinStop ; // 中奖停止

    private  Integer  betCount ;  //每期的投注数量

    private  Integer betMinMoney ; // 最小投注

    private  String lotteryId; //彩种id

    private  String lotteryName; //彩种名称

    private  String  wfId;  //玩法id

    private  String  wfName; //玩法名称


    private  Long  userPayMoney ; // 用户自购金额

    private  Long   gdTotalMoney ; //跟单总金额

    private  Integer  gdTotalPeople ; // 跟单人数

    private  Long    createValue;  //本单创造价值

    private  String   content;   //展示内容

    private  String  kjShowHm;  // 展示号码

    private  String   checkFlag; //审核标志，1通过0未审核2不通过

    private  String   orderStatus; //0:进行中 1:获胜,2:未中奖,3:撤销

    private  String   finishStatus; // 结束状态，0：正在进行，1:结束

    private String beginDate;         // 起始日期
    private String endDate;           //结束日期


}
