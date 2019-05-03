/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.common.base.entity.TreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 机构Entity
 * @author jeeplus
 * @version 2013-05-15
 */
@Data
public class Office extends NdataEntity {

//	private Office parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private Area area;		// 归属区域
	private String code; 	// 机构编码
//	private String name; 	// 机构名称
//	private Integer sort;		// 排序
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; 	// 负责人
	private String phone; 	// 电话
	private String fax; 	// 传真
	private String email; 	// 邮箱
	private String useable;//是否可用
	private User primaryPerson;//主负责人
	private User deputyPerson;//副负责人
	private List<String> childDeptList;//快速添加子部门
	
	public Office(){
		this.type = "2";
	}


}