package com.jeeplus.common.base.constant;

public enum  LogModularEnum {
    HUODONG("活动入款审核"),
    DATA_EDIT("会员信息修改"),
    CHARGE_RENGONG("手动入款"),
    WITHDRAW_RENGONG("手动提款"),
    CHARGE_SHENHE("充值审核"),
    WITHDRAW_SHENHE("取款审核");
    String name;

    LogModularEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
