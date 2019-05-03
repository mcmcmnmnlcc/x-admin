package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class ChargeMoneySelect extends NdataEntity {
    private Integer money;		// 金额
    private String content;		// 内容
    private String isHot;		//是否热门
}
