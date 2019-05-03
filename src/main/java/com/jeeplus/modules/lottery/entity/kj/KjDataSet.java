package com.jeeplus.modules.lottery.entity.kj;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class KjDataSet extends NdataEntity {
    private String kpDate;		// 开奖日期
    private String lotteryId;		// 彩种id
    private String lotteryQh;		// 开奖期号
    private String kjCode;		// 开奖号码
    private Date qhKpTime;		// 期号开盘时间
    private Date lockTime;		// 期号锁盘时间
    private Date planKjTime;		// 预计开奖时间
    private Date realKjTime;		// 实际开奖时间
    private String status;		// 开奖状态
    private String isManual;

    private String beginDate;//lottery start date
    private String endDate;//lottery end date
    private String beginQh;//start with lottery qh
    private String endQh;//end with lottery qh

    private String lotteryName;

    private  String  isChange;  //开奖是否异常
}
