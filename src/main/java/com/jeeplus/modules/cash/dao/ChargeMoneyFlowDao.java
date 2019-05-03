package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;

import java.util.List;

public interface ChargeMoneyFlowDao {

    /**
     * 根据明细表id查询充值记录
     * @param id
     * @return
     */
    public PfmUserChargeWithDraw get(String id);

    /**
     * 多条件查询充值记录，入款方式为1在线入款
     * 查询条件，订单号，付款人，用户名(登陆账号id)等等
     * @return
     */
    public List<PfmUserChargeWithDraw> findList(PfmUserChargeWithDraw pfmUserChargeWithDraw);

    /**
     * 保存充值记录
     * @param pfmUserChargeWithDraw
     */
    public void instUserCharge(PfmUserChargeWithDraw pfmUserChargeWithDraw);
}
