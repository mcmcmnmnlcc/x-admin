/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeeplus.common.base.entity.DataEntity;
import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.common.utils.UserUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单Entity
 * @author jeeplus
 * @version 2013-05-15
 */

@Data
public class Menu extends NdataEntity {

	private Menu parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private List<Menu> children;	// 子菜单

	private String name; 	// 名称
	private String href; 	// 链接
	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; 	// 图标
	private Integer sort; 	// 排序
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String permission; // 权限标识
	
	private String userId;
	
	public Menu(){
		this.sort = 30;
		this.isShow = "1";
	}


}