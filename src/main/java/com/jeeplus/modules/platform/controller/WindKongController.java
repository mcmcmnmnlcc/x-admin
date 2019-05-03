package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.platform.entity.WindKong;
import com.jeeplus.modules.platform.service.WindKongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 风控管理
 */
@RestController
@RequestMapping(value = "/platform/windkong/")
public class WindKongController {
    @Autowired
    WindKongService windKongService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(WindKong windKong, Integer pageNo, Integer pageSize) {
        PageBean<WindKong> page = windKongService.findList(new PageBean<WindKong>(pageNo, pageSize), windKong);
        Result result = new Result(true, StatusCode.OK, "查询成功", page);
        return result;
    }
}

