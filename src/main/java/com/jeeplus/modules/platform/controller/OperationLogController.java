package com.jeeplus.modules.platform.controller;

import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.controller.BaseController;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.modules.platform.entity.OperationLog;
import com.jeeplus.modules.platform.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pfm/operationLog")
public class OperationLogController extends BaseController {
    @Autowired
    OperationLogService operationLogService;

    /**
     * 查询日志列表
     * @param operationLog
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(OperationLog operationLog, Integer pageNo, Integer pageSize) {
        PageBean<OperationLog> page = operationLogService.findList(new PageBean<OperationLog>(pageNo, pageSize), operationLog);
        Result result = new Result(true, StatusCode.OK, "查询成功", page);
        return result;
    }
}
