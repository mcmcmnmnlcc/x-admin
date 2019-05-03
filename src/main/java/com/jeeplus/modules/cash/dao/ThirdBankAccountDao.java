package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.ThirdAccountNo;

import java.util.List;

public interface ThirdBankAccountDao {

    /**
     * 根据渠道id查询
     * @param id
     * @return
     */
    public ThirdAccountNo get(String id);

    /**
     * 按条件查询列表
     * @return
     */
    public List<ThirdAccountNo> findList(ThirdAccountNo thirdAccountNo);

    /**
     * 新增一个充值渠道信息
     * @param thirdAccountNo
     * @return
     */
    public int insert(ThirdAccountNo thirdAccountNo);

    /**
     * 修改一个充值渠道信息
     * @param thirdAccountNo
     * @return
     */
    public int update(ThirdAccountNo thirdAccountNo);

    /**
     * 根据渠道id删除
     * @param id
     * @return
     */
    public int delete(String id);

}
