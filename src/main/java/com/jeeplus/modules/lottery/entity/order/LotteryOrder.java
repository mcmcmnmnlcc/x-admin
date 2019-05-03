package com.jeeplus.modules.lottery.entity.order;

import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.modules.lottery.entity.LotterySet;
import com.jeeplus.modules.lottery.entity.kj.KjDataSet;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import lombok.Data;

import java.util.Date;

@Data
public class LotteryOrder extends NdataEntity {
    private KjDataSet kj;       // 开奖数据
    private LotterySet lottery;     // 彩票信息
    private WfMain wf;     // 玩法

    private String lotteryQh;          //期号
    private String wfName;          //玩法名称
    private  String pfmUserId;         //用户id
    private String orderNumber;		// 订单编号
    private String betNumber;		// 投注的号码
    private String kjNumber;        //开奖号码
    private Double betTimes;           //投注倍数
    private int betCount;           //投注数量
    private int winCount;           //中奖注数
    private double maxReturnPoint;  //返点数
    private double rewardMoney;        //奖金
    private double maxReturnMoney;  //返点金额
    private String profOrLoss;      //盈亏
    private String lotteryModes;    //投注单位 元角分
    private String betMoney;		// 投注金额
    private String betValidMoney;   // 有效投注额
    private String winMoney;		// 中奖金额
    private String status;		    // 状态
    private String zhuidan;		    // 所属追单（外键）
    private String zhuidanMode;     //追单模式  1为中奖后停止追单
    private String obtainDml;		//获得打码量

    private String beginDate;         // 起始日期
    private String endDate;           //结束日期
    private Integer bMoney;
    private Integer eMoney;

    private  String searchTime;  // 选择时间类型

    private Date kjDate; //开奖时间

    private String agent;

    //改写sql后分页用，数据库记录起始位置
    private int startIndex;
    private int pageSize;
}
