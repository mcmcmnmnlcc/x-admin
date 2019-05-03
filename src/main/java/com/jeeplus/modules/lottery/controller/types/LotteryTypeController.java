package com.jeeplus.modules.lottery.controller.types;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.lottery.entity.types.LotteryTypeEntity;
import com.jeeplus.modules.lottery.service.types.LotteryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/LotteryType/")
public class LotteryTypeController {
    @Autowired
    LotteryTypeService lotteryTypeService;

    /**
     * 列出彩票类型，这里彩票类型是从字典库sys_dict中查询的
     * @param activityConfig
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(LotteryTypeEntity activityConfig, Integer pageNo, Integer pageSize) {
        PageBean<LotteryTypeEntity> page = lotteryTypeService.findList(new PageBean<LotteryTypeEntity>(pageNo, pageSize), activityConfig);
        Result result = new Result(true, StatusCode.OK, "查询成功", page);
        return result;

    }
}
