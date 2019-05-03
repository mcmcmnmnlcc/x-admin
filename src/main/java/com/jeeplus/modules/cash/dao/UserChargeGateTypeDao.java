package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.UserChargeGateType;

import java.util.List;

public interface UserChargeGateTypeDao {
    public int get(String id);
    public List<UserChargeGateType> findList(UserChargeGateType userChargeGateType);
    public List<UserChargeGateType> findAllList();
    public int insert(UserChargeGateType userChargeGateType);
    public int update(UserChargeGateType userChargeGateType);
    public int delete(String id);
}
