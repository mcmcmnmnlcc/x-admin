package com.jeeplus.modules.lottery.entity.order;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;
@Data
public class LotteryOrderPl extends NdataEntity{
    private String wfFlag;
    private String orderNumber;
    private int plFlag;		// 赔率标识
    private int awardMoney;		// 获奖金额
    private int winCalcCount;		// 返点转换金额
}
