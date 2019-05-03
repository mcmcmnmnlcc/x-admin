package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.UserChargeGateType;
import com.jeeplus.modules.cash.service.UserChargeGateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cash/userChargeGateType")
public class UserChargeGateTypeController extends BaseController {
    @Autowired
    private UserChargeGateTypeService userChargeGateTypeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(UserChargeGateType userChargeGateType, Integer pageNo, Integer pageSize){
        PageBean<UserChargeGateType> page = userChargeGateTypeService.findList(new PageBean<UserChargeGateType>(pageNo,pageSize), userChargeGateType);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }
}
