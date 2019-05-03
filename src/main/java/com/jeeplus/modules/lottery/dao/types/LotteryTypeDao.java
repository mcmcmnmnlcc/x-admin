package com.jeeplus.modules.lottery.dao.types;

import com.jeeplus.modules.lottery.entity.types.LotteryTypeEntity;

import java.util.List;

public interface LotteryTypeDao {

    public List<LotteryTypeEntity> findList(LotteryTypeEntity lotteryTypeEntity);
    public int update(LotteryTypeEntity lotteryTypeEntity);
    public int delete(String id);
    public int insert(LotteryTypeEntity lotteryTypeEntity);
}
