package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import com.jeeplus.modules.cash.service.ChargeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员入款单
 * 入款列表Controller
 * 银行入款列表Controller
 */
@RestController
@RequestMapping(value = "/cash/in")
public class ChargeMoneyController extends BaseService {
    @Autowired
    ChargeMoneyService chargeMoneyService;

    @RequestMapping(value = "/list",method = RequestMethod.GET )
    public Result list(PfmUserChargeWithDraw pfmUserChargeWithDraw, Integer pageNo, Integer pageSize){
        PageBean<PfmUserChargeWithDraw> page = chargeMoneyService.findList(new PageBean<PfmUserChargeWithDraw>(pageNo,pageSize), pfmUserChargeWithDraw);
        Result result=new Result(true, StatusCode.OK,"查询成功",page);
        return result;

    }
}
