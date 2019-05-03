/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.lottery.entity.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.common.base.entity.TreeEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 玩法群组Entity
 * @author duke
 * @version 2017-03-29
 */
@Data
public class LotteryWfGroup extends NdataEntity {

	private String name;		// 玩法组名称
	private String groupFlag;		// 标识
	private String groupNumFlag;		// 数字标识
	private Integer sort;		// 排序
	private String lotteryType;		// 彩票类型
	private LotteryWfGroup parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String status;		// 群组状态

}