package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

/**
 * 银行收款账号Entity
 */
@Data
public class BankSkAccount extends NdataEntity {
    private Integer skTypeFlag;      //1银行，2支付宝，3微信,kk_user_charge_type
    private Integer skBankId;		// 收款银行ID
    private String bankNo;		// 银行账号
    private String bankName;		// 银行名称 --  作为支行名称
    private String skPeople;		// 收款人
    private String accountNo;		// 卡号
    private String accountKhName;		// 账号开户名
    private String accountKhAddress;		// 开户网点
    private String status;		// 状态
    private Integer chzhiMinMoney;		// 充值最低金额
    private Integer chizhiMaxMoney;		// 充值最高金额
    private Integer sort;		// 排序
    private String bindUserLevel;		// 绑定分层
    private String bindUserLevelNames;		// 绑定分层名字


    private String skTypeName;      //收款渠道名称
    private String skBankName;      //收款银行名称
    private String typeState;

    private String  weixinAccount; //微信账号

    private String  payAccount;  // 支付宝账户

    private String  qrcode;  // 二维码
}
