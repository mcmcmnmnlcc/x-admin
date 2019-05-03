package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class UserChargeGateType extends NdataEntity {
    private String gateType;           //网关类型
    private String gateName;            //网关名称
    private String gateFlag;           //网关标识
    private Integer sort;              //排序
    private String status;             //状态
    private String gateImg;            //网关图标
    private Long minMoney;
    private Long maxMoney;
    private String reserved;

}
