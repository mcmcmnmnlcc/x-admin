package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.platform.dao.WindKongDao;
import com.jeeplus.modules.platform.entity.WindKong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WindKongService extends BaseService {
    @Autowired
    WindKongDao windKongDao;

    public PageBean<WindKong> findList(PageBean<WindKong> pageBean, WindKong windKong){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<WindKong> list=windKongDao.findList(windKong);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }

}
