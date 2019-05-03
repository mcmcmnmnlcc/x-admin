package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import com.jeeplus.modules.cash.service.UserOutMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员出款单Controller
 * 出款列表Controller
 */
@RestController
@RequestMapping(value = "/cash/out")
public class OutMoneyController extends BaseController {
    @Autowired
    UserOutMoneyService userOutMoneyService;

    /**
     * 查询出口信息列表
     * @param pfmUserChargeWithDraw
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(PfmUserChargeWithDraw pfmUserChargeWithDraw, Integer pageNo, Integer pageSize){
        PageBean<PfmUserChargeWithDraw> page = userOutMoneyService.findList(new PageBean<PfmUserChargeWithDraw>(pageNo,pageSize), pfmUserChargeWithDraw);
        Result result=new Result(true, StatusCode.OK,"查询出款信息成功",page);
        return result;

    }
}
