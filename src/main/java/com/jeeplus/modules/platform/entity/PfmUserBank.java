package com.jeeplus.modules.platform.entity;


import lombok.Data;

@Data
public class PfmUserBank {
    private String userId;
    private String userName;
    private String bankNo;                         //开户行
    private String bankBranchNo;                   //开户支行
    private String accountNo;                      //卡号
    private String bankStatus;                     //是否绑定银行卡   1：绑定   0未绑定
    private String updateTime;
    private String createTime;
    private String beginDate;
    private String endDate;
    private String bankId; //银行卡ID


}
