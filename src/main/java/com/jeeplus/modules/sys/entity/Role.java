/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;


/**
 * 角色Entity
 */
@Data
public class Role extends NdataEntity {

	private Office office;	// 归属机构
	private String name; 	// 角色名称
	private String enname;	// 英文名称
	private String roleType;// 权限类型
	private String dataScope;// 数据范围
	
	private String oldName; 	// 原角色名称
	private String oldEnname;	// 原英文名称
	private String sysData; 		//是否是系统数据
	private String useable; 		//是否是可用


	public Role(){
	}
	public Role(String id){
		this.id=id;
	}

	


}
