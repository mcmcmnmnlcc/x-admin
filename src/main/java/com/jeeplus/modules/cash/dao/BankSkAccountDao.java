package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.BankSkAccount;

import java.util.List;

/**
 * 收款方式的管理(估计是公司的收款方式和信息)
 */
public interface BankSkAccountDao {

    /**
     * 按条件查询
     * @param bankSkAccount
     * @return
     */
    public List<BankSkAccount> findList(BankSkAccount bankSkAccount);

    /**
     * 查询全部
     * @return
     */
    public List<BankSkAccount> findAllList();

    /**
     * 插入
     * @param bankSkAccount
     * @return
     */
    public int insert(BankSkAccount bankSkAccount);

    /**
     * 更新一条数据
     * @param id
     * @return
     */
    public int update(String id);

    public int delete(String id);

    /**
     * 获取用某种支付类型支付的总数，比如支付宝支付的有多少次
     * @param skTypeFlag
     * @return
     */
    public int getStatusCount(Integer skTypeFlag);



}
