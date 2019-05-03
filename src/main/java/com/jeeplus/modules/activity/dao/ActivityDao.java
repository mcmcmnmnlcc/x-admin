package com.jeeplus.modules.activity.dao;

import com.jeeplus.modules.activity.entity.Activity;

import java.util.List;

public interface ActivityDao  {
    public List<Activity> findList(Activity activity);
}
