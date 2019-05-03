package com.jeeplus.common.log.factory;

import com.jeeplus.modules.platform.entity.OperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @author eden
 */
public class LogFactory {

    public static OperationLog createOperationLog(String userId, String optModular, String content, String returnMsg, String optIp, Integer status, String optAdmin) {
        OperationLog operationLog = new OperationLog();
        operationLog.setUserId(userId);
        operationLog.setOptModular(optModular);
        operationLog.setContent(content);
        operationLog.setReturnMsg(returnMsg);
        operationLog.setStatus(status+"");
        operationLog.setOptIp(optIp);
        operationLog.setCreateTime(new Date());
        //操作管理员
        operationLog.setOptAdmin(optAdmin);
        return operationLog;
    }

}
