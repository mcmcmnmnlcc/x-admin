package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import com.jeeplus.modules.cash.service.MoneyFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资金流水明细查询
 * 资金流水明细Controller
 */
@RestController
@RequestMapping(value = "/money/flow")
public class MoneyFlowController extends BaseController{
    @Autowired
    MoneyFlowService moneyFlowService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(UserMoneyFlow userMoneyFlow, Integer pageNo, Integer pageSize){


        PageBean<UserMoneyFlow> page = moneyFlowService.findList(new PageBean<UserMoneyFlow>(pageNo,pageSize), userMoneyFlow);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);


        return result;

    }
}
