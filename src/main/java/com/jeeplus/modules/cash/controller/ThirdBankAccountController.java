package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.ThirdAccountNo;
import com.jeeplus.modules.cash.service.ThirdBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/third/bank")
public class ThirdBankAccountController extends BaseController {
    @Autowired
    ThirdBankAccountService thirdBankAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(ThirdAccountNo bankSkAccount, Integer pageNo, Integer pageSize){
        PageBean<ThirdAccountNo> page = thirdBankAccountService.findList(new PageBean<ThirdAccountNo>(pageNo,pageSize), bankSkAccount);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }

}
