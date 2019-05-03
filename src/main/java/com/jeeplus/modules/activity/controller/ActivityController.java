package com.jeeplus.modules.activity.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.activity.entity.Activity;
import com.jeeplus.modules.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activity/")
public class ActivityController extends BaseController {
    @Autowired
    ActivityService activityService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(Activity activity, Integer pageNo, Integer pageSize){
        PageBean<Activity> page = activityService.findList(new PageBean<Activity>(pageNo,pageSize), activity);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }

}
