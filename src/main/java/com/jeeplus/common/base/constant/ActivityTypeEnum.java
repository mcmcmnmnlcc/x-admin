package com.jeeplus.common.base.constant;

/**
 * 活动类型枚举
 * @author eden
 * @create 2018-01-23 19:38
 **/
public enum ActivityTypeEnum {
    HD_QIANDAO("hd_qiandao", "签到"),
    HD_CHARGE("hd_charge", "首次充值"),
    HD_ZLCJ("hd_zlcj", "助力彩金"),
    HD_XRKH("hd_xrkh", "新人开户"),
    HD_FXPYQ("hd_fxpyq", "分享朋友圈"),;


    private String hdType;
    private String comment;

    ActivityTypeEnum(String hdType, String comment) {
        this.hdType = hdType;
        this.comment = comment;
    }

    public String getHdType() {
        return hdType;
    }

    public void setHdType(String hdType) {
        this.hdType = hdType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
