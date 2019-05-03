package com.jeeplus.modules.lottery.dao.order;

import com.jeeplus.modules.lottery.entity.order.LotteryOrder;

import java.util.List;

public interface LotteryOrderDao {
    /**
     * 根据id获取彩票订单
     * @param id
     * @return
     */
    public LotteryOrder get(String id);

    /**
     * 多条件查询彩票单
     * @param lotteryOrder
     * @return
     */
    public List<LotteryOrder> findList_v2(LotteryOrder lotteryOrder);
    public int getCount();

    /**
     * 查询所有记录
     * @return
     */
    public List<LotteryOrder> findAllList();
}
