package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;


import java.util.Date;

/**
 * 充值或者提款明细表
 */
@Data
public class PfmUserChargeWithDraw extends NdataEntity {
    private String userId;
    private String orderNo;              // 充值 订单号
    private String changeTypeStatus;     //05:充值  06提款
    private Long changeMoney;            // 改变金额

    private String status;               //充值05：1已支付2支付失败3未支付。提现06：1通过2不通过3待审核
    private String payUser;              //充值：付款人
    private Integer payType;             // 充值  渠道
    private String rkType;               //充值：入款方式 1:在线入款  2:银行入款 3:人工入款
    private String skBank;               //充值  入款银行
    private String skAccount;            //充值  收款账号

    private String checkPerson;          //审核人
    private String checkInfo ;		     // 审核信息
    private Date createDate;             //创建时间
    private Date checkDate;              // 审核时间


    private String accountNo;          // 银行卡号
    private String userName;           // 开户名
    private String bankNo;             //开户银行
    private String logIp;
    private String agent;
    private String agentBy;

    private String changeMoneyStr;     //显示改变的金额
    private String beginDate;
    private String endDate;

    private String typeName;           //入款渠道
    private String checkStatus;        // 审核状态   1通过2不通过
    private String levelName;            //用户层级名称
    private Integer bMoney;
    private Integer eMoney;

    private Long currentMoney;     //当前余额

    private String currentMoneyStr;   //当前余额

    private String  bankBranchNo;    //开户支行

    private Integer  todayOutCounts; // 今日出款次数

    private  String firstOutMoney; // 今日出款次数

    private String createDateStr;  // 创建时间字符串格式

    private Integer flashTime;  // 页面刷新用的

    private  String  searchPersonInfo;  //搜索用户信息用的

    private String   searchContext;  // 模糊搜索

    private  String  searchTime;  // 搜索时间类型

    private  String  platformName; // 支付平台

    private  String  lockFlag; // 是否锁住

    private  String  budanFlag; // 是否补单


    private  String  recoverFlag; // 是否显示恢复按钮

    private   String  skPerson ; // 收款人

    private Long countChukuanMoney;	//'累计出款金额，千为单位',用来判断首次出款

    public PfmUserChargeWithDraw(){

    }

    public PfmUserChargeWithDraw(String userId, String changeTypeStatus, Long changeMoney, String status) {
        this.userId=userId;
        this.changeTypeStatus = changeTypeStatus;
        this.changeMoney = changeMoney;
        this.status = status;
        this.createDate=new Date();
    }


}

