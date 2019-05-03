package com.jeeplus.common.base.constant;

/**
 * 流水表状态码
 * @author eden
 * @create 2018-01-20 19:38
 **/
public enum MoneyFlowStatusEnum {
    CHONGZHI_TYPE("05", "充值"),
    TIKUAN_TYPE("06", "提款"),
    XIAZHU_TYPE("01", "下注"),
    ZHONGJIANG_TYPE("02", "中奖"),
    FANSHUI_TYPE("03", "反水"),
    CHEDAN_TYPE("04", "撤单"),
    GDZYJ_TYPE("10", "跟单赚佣金"),
    GDFYJ_TYPE("11", "跟单付佣金"),
    HUODONG_TYPE("20", "活动"),
    HUODONG_COMPANY_TYPE("21", "公司入款优惠"),

    TYPE_SDCR_01_04("04","入款-银行入款"),
    TYPE_SDCR_04_05("05","彩票-开奖异常退还"),

    TYPE_SDCR_07_01("01","彩票返水"),
    TYPE_SDCR_07_02("02","彩票返水撤销"),

    TYPE_SDCR_07_03("03","六合彩返水"),
    TYPE_SDCR_07_04("04","六合彩返水撤销"),

    TYPE_SDCR_10_01("01","人工入款");




    private String changeTypeStatus;
    private String comment;

    MoneyFlowStatusEnum(String changeTypeStatus, String comment) {
        this.changeTypeStatus = changeTypeStatus;
        this.comment = comment;
    }

    public String getChangeTypeStatus() {
        return changeTypeStatus;
    }

    public void setChangeTypeStatus(String changeTypeStatus) {
        this.changeTypeStatus = changeTypeStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
