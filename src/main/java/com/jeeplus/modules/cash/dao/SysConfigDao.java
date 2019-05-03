package com.jeeplus.modules.cash.dao;

import com.jeeplus.modules.cash.entity.SysConfigEntity;

import java.util.List;

public interface SysConfigDao {
    List<SysConfigEntity> findSysConfigByType(String type);

    String getAgentByFlag(String key);
}
