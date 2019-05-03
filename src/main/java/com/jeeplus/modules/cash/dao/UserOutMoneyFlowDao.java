package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Date;
import java.util.List;

public interface UserOutMoneyFlowDao {
    /**
     * 根据明细表id查询出款记录
     * @param id
     * @return
     */
    public PfmUserChargeWithDraw get(String id);

    /**
     * 多条件查询出款记录
     *
     * @return
     */
    public List<PfmUserChargeWithDraw> findList(PfmUserChargeWithDraw pfmUserChargeWithDraw);

    /**
     * 修改用户出款的审核状态
     * @param pfmUserChargeWithDraw
     * @return
     */
    public int editDrawUserCash(PfmUserChargeWithDraw pfmUserChargeWithDraw);

    /**
     * 提款失败的处理，把提款金额加到用户的返回金额字段，然后冻结金额字段减去提款金额
     * @param pfmUserChargeWithDraw
     * @return
     */
    public int editUserMoneyWithdrawFail(PfmUserChargeWithDraw pfmUserChargeWithDraw);

    /**
     * 提款成功的处理
     * 冻结金额字段减去提款金额,
     * 累计出款次数+1
     * 累计出款金额+提款金额
     * @param pfmUserChargeWithDraw
     * @return
     */
    public int editUserMoneyWithdrawSuccss(PfmUserChargeWithDraw pfmUserChargeWithDraw);

    /**
     * 获取用户的彩票盈亏金额
     * @param userId 用户
     * @param createDate    购买彩票的时间
     * @return
     */
    public double caclUserPlMoney(String userId, Date createDate);

    /**
     * 统计总的出款金额
     * 可以根据用户名等查询
     * @param pfmUserChargeWithDraw
     * @return
     */
    public Long getCountChargeMomeny(PfmUserChargeWithDraw pfmUserChargeWithDraw);

}
