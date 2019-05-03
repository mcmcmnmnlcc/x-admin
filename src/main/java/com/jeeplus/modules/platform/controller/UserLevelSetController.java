package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.platform.entity.UserLevelSet;
import com.jeeplus.modules.platform.service.UserLevelSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员层级管理
 */
@RestController
@RequestMapping(value = "/platform/leveset/")
public class UserLevelSetController {
    @Autowired
    UserLevelSetService userLevelSetService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(UserLevelSet userLevelSet, Integer pageNo, Integer pageSize){
        PageBean<UserLevelSet> page = userLevelSetService.findList(new PageBean<UserLevelSet>(pageNo,pageSize), userLevelSet);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;
    }

}
