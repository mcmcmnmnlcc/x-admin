package com.jeeplus.common.base.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.Page;
import com.jeeplus.common.base.dao.CrudDao;
import com.jeeplus.common.base.entity.DataEntity;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>>{
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(page.getPageNo(), page.getPageSize(),true);
        entity.setPage(page);
        List<T> list=dao.findList(entity);
        page.setList(list);
        page.setCount(p.getTotal());
        return page;
    }

}
