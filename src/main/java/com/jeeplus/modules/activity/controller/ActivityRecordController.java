package com.jeeplus.modules.activity.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.activity.entity.ActivityRecord;
import com.jeeplus.modules.activity.service.ActivityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ActivityRecord/")
public class ActivityRecordController extends BaseController {
    @Autowired
    ActivityRecordService activityRecordService;

    /**
     * 活动领取的记录查询，后台好像只能查询，还能做一些审核的操作
     * @param activityRecord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(ActivityRecord activityRecord, Integer pageNo, Integer pageSize) {
        PageBean<ActivityRecord> page = activityRecordService.findList(new PageBean<ActivityRecord>(pageNo, pageSize), activityRecord);
        Result result = new Result(true, StatusCode.OK, "查询成功", page);
        return result;
    }


}
