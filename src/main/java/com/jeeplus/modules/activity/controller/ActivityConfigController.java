package com.jeeplus.modules.activity.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.activity.entity.ActivityConfig;
import com.jeeplus.modules.activity.service.ActivityConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activityConfig/")
public class ActivityConfigController {
    @Autowired
    ActivityConfigService activityConfigService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(ActivityConfig activityConfig, Integer pageNo, Integer pageSize) {
        PageBean<ActivityConfig> page = activityConfigService.findList(new PageBean<ActivityConfig>(pageNo, pageSize), activityConfig);
        Result result = new Result(true, StatusCode.OK, "查询成功", page);
        return result;

    }
}
