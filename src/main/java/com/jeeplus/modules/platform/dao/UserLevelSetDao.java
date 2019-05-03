package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.UserLevelSet;

import java.util.List;

public interface UserLevelSetDao {

    /**
     * 根据id查询一个层级
     * @param id
     * @return
     */
    public UserLevelSet get(String id);

    /**
     * 查询所有的层级
     * @return
     */
    public List<UserLevelSet> findAllList();
    /**
     * 查询所有的层级(可以带条件)
     * @return
     */
    public List<UserLevelSet> findList(UserLevelSet userLevelSet);

    /**
     * 插入一个层级
     * @param userLevelSet
     * @return
     */
    public int insert(UserLevelSet userLevelSet);

    /**
     * 更新层级
     * @return
     */
    public int update(UserLevelSet userLevelSet);

    /**
     * 删除层级
     * @param id
     * @return
     */
    public int delete(String id);

    /**
     * 查询属于某个层级的会员数量
     * @param id
     * @return
     */
    public int getCountByLevelId(String id);
}
