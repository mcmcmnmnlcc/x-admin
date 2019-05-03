package com.jeeplus.modules.activity.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.modules.activity.dao.ActivityDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class Activity extends NdataEntity {
    private String imageUrl;		// 头像地址
    private String title;		// 标题
    private String turnUrl;		// 跳转地址
    private String status;		// 状态 1展示，0不展示
    private String activityType;		// 活动类型01:限时优惠,02:免费活动,03:vip专享
    private Integer hotClick;		// 热度
    private String hdOnOff;		//开关 1开启0关闭
    private String hdType;		//活动类型
    private Date createTime;		// 创建时间

}
