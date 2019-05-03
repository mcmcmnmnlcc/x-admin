/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import com.jeeplus.common.base.entity.TreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 区域Entity
 * @author jeeplus
 * @version 2013-05-15
 */
@Data
public class Area extends NdataEntity {

//	private Area parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private String code; 	// 区域编码
//	private String name; 	// 区域名称
	private Integer sort;		// 排序
	private String type; 	// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
	
	public Area(){
		super();
		this.sort = 30;
	}

}