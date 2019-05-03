package com.jeeplus.modules.activity.dao;

import com.jeeplus.modules.activity.entity.ActivityConfig;

import java.util.List;

public interface ActivityConfigDao {
    public List<ActivityConfig> findList(ActivityConfig activity);
}
