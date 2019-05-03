package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UserMoneyFlow extends NdataEntity {
    private String userId;
    private String gamesFlag;          //游戏种类
    private String changeType;         //流水类型
    private String changeTypeStatus;  //改变状态标识   05充值 06提款 01下注 02中奖 03反水 04撤单
    private String coinChangeName;    //改变状态描述
    private Long changeCoinInt;        //待变化的钱
    private Long balanceInt;           //改变后的余额
    private String remark;              //备注
    private String remark2;             //充值：1已支付2支付失败3未支付。出款：1通过2不通过3审核中
    private String timeCreated;        //时间
    private String flagName;           //充值或者提款状态说明
    private String flag;                //状态表示
    private String statusName;			//状态前台显示
    private String optUser;//操作人

    private String accountNo;          // 银行卡号
    private String userName;           // 开户名
    private String bankNo;             //开户银行
    private String changeCoin;         //以string类型，防止科学计数法
    private String balance;		       // 改变后的金额
    private String checkInfo;          // 审核信息
    private String checkPerson;        // 审核人
    private Date checkDate;          //审核时间

    private String checkStatus;        // 审核状态   1通过2不通过

    private String beginDate;
    private String endDate;

    public UserMoneyFlow(){

    }

    public UserMoneyFlow(String user_id, String change_type_status,Long change_coin_int,Long balance_int,String gamesFlag,String changeType) {
        this.userId = user_id;
        this.changeTypeStatus = change_type_status;
        this.changeCoinInt = change_coin_int;
        this.balanceInt = balance_int;
        this.gamesFlag=gamesFlag;
        this.changeType=changeType;
    }
}
