/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.dao;


import com.jeeplus.modules.sys.entity.Role;

import java.util.List;

/**
 * 角色DAO接口
 * @author jeeplus
 * @version 2013-12-05
 */
public interface RoleDao {
    public List<Role> getByUserId(String userId);



}
