package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.OperationLog;

import java.util.List;

public interface OperationLogDao {

    /**
     * 根据id查询日志
     * @param id
     * @return
     */
    public OperationLog get(String id);
    /**
     * 主动插入数据
     * @param operationLog
     * @return
     */
    public int insert(OperationLog operationLog);
    public int update(OperationLog operationLog);
    public int delete(String id);
    public List<OperationLog> findList(OperationLog operationLog);

}
