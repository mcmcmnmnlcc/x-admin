package com.jeeplus.modules.activity.dao;

import com.jeeplus.modules.activity.entity.ActivityRecord;

import java.util.List;

public interface ActivityRecordDao {
    public List<ActivityRecord> findList(ActivityRecord activityRecord);

}
