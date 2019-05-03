package com.jeeplus.modules.activity.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityRecord extends NdataEntity {
    private String activityId;		// 外键（活动主题）
    private String userId;		// 外键（用户编号）
    private Date applyTime;		// 申请时间
    private String applyInfo;		// 申请信息
    private Long awardMoney;		// 奖励金额
    private String status;		// 审核状态 1:通过,2:不通过,0审核中
    private String checkPeople;  //审核人
    private String hdType;		// 活动类型
    private String hdTypeDetail;		// 活动类型明细
    private String hdTypeDetailDesc;		// 活动类型明细描述
    private String logIp;		// 领取ip
    private String beginDate;
    private String endDate;
}
