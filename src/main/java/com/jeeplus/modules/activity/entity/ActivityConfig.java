package com.jeeplus.modules.activity.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class ActivityConfig extends NdataEntity {
    private String activityId;		// 活动id
    private String hdType;		// 活动类型
    private String label;		// label
    private String label2;		// label2
    private String value;		// value
    private String value2;		// value2
    private String hdTypeDetail;		// 活动类型明细
    private String flowTimes;		// 流水倍数
    private String sort;		// 排序
    private String description;		// 描述
    private String onOff;		// 开关
    private String createTime;		// 创建时间
    private String activityName;

}
