package com.jeeplus.modules.lottery.dao.kj;

import com.jeeplus.modules.lottery.entity.kj.KjDataSet;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface KjDataSetDao {

    /**
     * 批量插入开奖数据的map,有以下键值对：
     *   date:   2017-04-19
     *   date+1:2017-04-20
     *   dateFormat: 20170419
     *   lockSeconds:提前锁定的秒数
     *   lotteryId: 彩票的种类id
     *
     * @param map
     */
    public void instBatchKjData(Map<String,Object> map);

    int  getDayLastLotteryQh(String lotteryId);

    public void saveKjData(KjDataSet kjEntity);

    public String getPk10LotteryQh(@Param("lotteryId")String lotteryId, @Param("lastDay")String lastDay);

    /**
     * 获取六合彩未开奖的最后一期
     * @return
     */
    public KjDataSet getLhcLastEntity();

    /**
     * 手动和自动开奖的切换
     * @param id
     */
    public void lockOrOpen(String id);

    /**
     * 把开奖状态设置为3，应该是异常状态
     * @param id
     */
    public void updateKjDataSetCx(String id);

    /**
     * 根据彩票标识获取彩票名称
     * @param lotteryId
     * @return
     */
    public String getLotteryName(String lotteryId);


}
