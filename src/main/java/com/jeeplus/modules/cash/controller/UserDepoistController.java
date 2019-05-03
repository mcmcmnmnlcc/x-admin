package com.jeeplus.modules.cash.controller;

import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.modules.cash.service.ChargeMoneyService;
import com.jeeplus.modules.cash.service.PfmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDepoistController extends BaseController {

    @Autowired
    private PfmUserService pfmUserService;

    @Autowired
    private ChargeMoneyService chargeMoneyService;



}
