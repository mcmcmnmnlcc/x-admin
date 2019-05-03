package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.platform.entity.PfmUserBank;
import com.jeeplus.modules.platform.service.PfmUserBankService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收款方式的管理(估计是公司的收款方式和信息)
 */
@RestController
@RequestMapping(value = "/platform/userbank")
public class PfmUserBankController extends BaseController {
    @Autowired
    PfmUserBankService pfmUserBankService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(PfmUserBank pfmUserBank, Integer pageNo, Integer pageSize){
        PageBean<PfmUserBank> page = pfmUserBankService.findList(new PageBean<PfmUserBank>(pageNo,pageSize), pfmUserBank);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }

}
