/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.dao;

import com.jeeplus.common.annotation.MyBatisDao;
import com.jeeplus.common.base.dao.CrudDao;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
public interface UserDao {

	public User getById(String id);

	/**
	 * 根据登录名获取权限标识
	 * @param loginName
	 * @return
	 */
	public List<String> getPermissionByUsername(String loginName);

	/**
	 * 根据用户名查询
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(String loginName);

	/**
	 * 插入用户数据
	 * @param user
	 * @return
	 */
	public int insert(User user);

    /**
     * 插入用户拥有的角色数据(在user_role中间表插入数据)
     * @param roleIds
     * @return
     */
	public int insertRoles(List<UserRole> roleIds);
    /**
     * 删除用户数据
     * @param userId 用户
     * @return
     */
	public int delete(String userId);
    /**
     * 删除用户拥有的角色数据(在user_role中间表删除数据)
     * @param userId
     * @return
     */
    public int deleteUserRole(String userId);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int update(User user);

}
