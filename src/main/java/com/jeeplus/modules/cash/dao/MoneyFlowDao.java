package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoneyFlowDao {
    public List<UserMoneyFlow> findList(UserMoneyFlow userMoneyFlow);
    public void saveMoneyFlow(UserMoneyFlow flow);
    public void editPfmUserMoney(@Param("userId") String userId,@Param("changeCoinInt") Long changeCoinInt);
}
