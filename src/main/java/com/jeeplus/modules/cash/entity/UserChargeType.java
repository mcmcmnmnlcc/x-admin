package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class UserChargeType extends NdataEntity {
    private String typeFlag;            //网关类型标志
    private String typeName;            //网关类型名称
    private Integer minMoney;           //最小金额
    private Integer maxMoney;           // 最大金额
    private String sort;                 //排序
    private String status;              //状态
    private String type;                //类型
    private String position;             //在kk_third_account_no,type_flag字段的位置
    private String reserved;
}
