/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.dao;

import com.jeeplus.common.base.dao.CrudDao;
import com.jeeplus.common.base.dao.NcrudDao;
import com.jeeplus.modules.sys.entity.Menu;

import java.util.List;

/**
 * 菜单DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */

public interface MenuDao{

	public List<Menu> findByUserId(Menu menu);
	public int insert(Menu menu);
	public int update(Menu menu);

	/**
	 * 根据id查询父id
	 * @param id
	 * @return
	 */
	public String findParentId(String id);

}
