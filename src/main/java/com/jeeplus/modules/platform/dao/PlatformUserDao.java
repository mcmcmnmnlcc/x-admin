package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.PlatformUser;

import java.util.List;

public interface PlatformUserDao {
    /**
     * 根据会员表的id查询(这个信息量比较全)
     * @param id
     * @return
     */
    public PlatformUser get(String id);

    /**
     * 根据会员的登录名查询
     * @param userId
     * @return
     */
    public PlatformUser get2(String userId);

    /**
     * 也是根据会员的登录名查询(这个信息量最少)
     * @param user_id
     * @return
     */
    public PlatformUser findPfmUserByUserId(String user_id);


    /**
     * 多条件查询
     * 其中的参数字段searchPersonInfo表示搜索的类型
     *  searchContext 表示搜索的类容，详情请看xml文件
     * @param platformUser
     * @return
     */
    public List<PlatformUser> findList(PlatformUser platformUser);

    /**
     * 修改平台用户的信息
     * @param platformUser
     * @return
     */
    public int editUserProp(PlatformUser platformUser);

    /**
     * 查询今天用户注册数量
     * @return
     */
    public int todayRegistUserNum();

    /**
     *查询今天用户注册数量(某个代理的)
     * @return
     */
    public int todayAgentRegistUserNum(String agent);

    /**
     *  修改会员银行信息
     */
    public void updateUserBankInfo(PlatformUser platformUser);

    /**
     * 查询数据库中是否含有一个realName
     * @param realName
     * @return
     */
    public int existUserName(String realName);

    public void editPfmUserProp(PlatformUser platformUser);


}
