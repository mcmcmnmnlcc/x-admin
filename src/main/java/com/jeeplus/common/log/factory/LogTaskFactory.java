package com.jeeplus.common.log.factory;

import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.modules.platform.dao.OperationLogDao;
import com.jeeplus.modules.platform.entity.OperationLog;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @author eden
 */
@Slf4j
public class LogTaskFactory {

    private static OperationLogDao operationLogDao = ((OperationLogDao) SpringContextHolder.getBean("operationLogDao"));

    public static TimerTask operationLog(final String userId, final String optModular, final String content, final String returnMsg, final String optIp, final Integer status, final String optAdmin) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    OperationLog operationLog = LogFactory.createOperationLog(
                            userId, optModular, content, returnMsg, optIp, status, optAdmin);
                    operationLogDao.insert(operationLog);
                } catch (Exception e) {
                    log.error("创建操作日志异常!", e);
                }
            }
        };
    }
}
