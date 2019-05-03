package com.jeeplus.modules.lottery.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

/**
 * 彩种设置
 */
@Data
public class LotterySet extends NdataEntity {

    private String lotteryImage;		// 彩票图片
    private String lotteryName;		// 彩票名称
    private String flag;		// 彩票标识
    private String numFlag;		// 数字标识
    private Integer sort;		// 排序
    private String status;		// 状态（0：正常；1：停售）
    private String lotteryType;		// 彩票类型
    private String showType;        //显示颜色
    private double winRateMin; 	// 奖池盈利率最小值
    private double  winRateMax;	//  奖池盈利率最大值

    private String recommend;	//是否是推荐彩种

    private Integer sortBy;		//推荐彩种排序
}
