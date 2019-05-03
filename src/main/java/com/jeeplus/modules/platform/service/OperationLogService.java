package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.platform.dao.OperationLogDao;
import com.jeeplus.modules.platform.entity.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperationLogService extends BaseService {
    @Autowired
    private OperationLogDao operationLogDao;

    public OperationLog get(String id){
        return operationLogDao.get(id);
    }

    /**
     * 按条件分页查询
     * @param pageBean
     * @param operationLog
     * @return
     */
    public PageBean<OperationLog> findList(PageBean<OperationLog> pageBean, OperationLog operationLog){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<OperationLog> list=operationLogDao.findList(operationLog);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 插入
     * @param operationLog
     */
    public void insert(OperationLog operationLog) {
        operationLogDao.insert(operationLog);
    }

    public void update(OperationLog operationLog) {
        operationLogDao.update(operationLog);
    }

    public void delete(String id) {
        operationLogDao.delete(id);
    }

}
