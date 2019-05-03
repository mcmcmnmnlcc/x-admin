/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.lottery.entity.wf;


import com.jeeplus.common.annotation.excel.ExcelField;
import com.jeeplus.common.base.entity.DataEntity;
import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

/**
 * 玩法设置Entity
 * @author duke
 * @version 2017-03-29
 */
@Data
public class WfPlChild extends NdataEntity {
	
	private static final long serialVersionUID = 1L;
	private WfMain wfMain;		// 外键 父类
	private String name;		// 赔率名称
	private String plFlag;		// 赔率标识
	private String numFlag;		// 数字标识
	private Double awardMoney;		// 获奖金额
	private String maxReturn;		// 最大返点数
	private Double baseAwardMoney;		// 代理基础奖励金额(元)
	private Double agentRebateMoney;		// 代理返点转换金额(元)
	private Double rebateConvertNum;		// 返点转换金额
	private Integer sort;		// 排序


}