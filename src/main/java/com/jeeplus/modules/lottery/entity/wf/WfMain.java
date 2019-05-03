/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.lottery.entity.wf;

import com.google.common.collect.Lists;
import com.jeeplus.common.annotation.excel.ExcelField;
import com.jeeplus.common.base.entity.DataEntity;
import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.modules.lottery.entity.group.LotteryWfGroup;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 玩法设置Entity
 * @author duke
 * @version 2017-03-29
 */
@Data
public class WfMain extends NdataEntity {

	private LotteryWfGroup wfGroup;		// 归属玩法群组
	private String name;		// 玩法名称
	private String wfFlag;		// 玩法标记
	private String numFlag;		// 数字标记
	private String lotteryTpye;		// 彩票类型
	private String wfExplain;		// 玩法说明
	private String example;		// 范例
	private String methodHelp;		// 帮助
	private String status;		// 状态
	private Integer sort;		// 排序
	private Integer maxZhus;	//最大注数，0为不限制
	private List<WfPlChild> wfPlChildList = Lists.newArrayList();		// 子表列表
	

}