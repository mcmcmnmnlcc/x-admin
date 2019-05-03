package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.BankSkAccount;
import com.jeeplus.modules.cash.service.BankSkAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司入款管理列表
 */
@RestController
@RequestMapping(value = "/cash/bankSkAccount")
public class BankSkAccountController extends BaseController {
    @Autowired
    BankSkAccountService bankSkAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(BankSkAccount bankSkAccount, Integer pageNo, Integer pageSize){
        PageBean<BankSkAccount> page = bankSkAccountService.findList(new PageBean<BankSkAccount>(pageNo,pageSize), bankSkAccount);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }
}
