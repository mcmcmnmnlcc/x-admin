package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.modules.platform.dao.UserLevelSetDao;
import com.jeeplus.modules.platform.entity.UserLevelSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserLevelSetService {
    @Autowired
    UserLevelSetDao userLevelSetDao;

    public PageBean<UserLevelSet> findList(PageBean<UserLevelSet> pageBean,UserLevelSet userLevelSet){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<UserLevelSet> list=userLevelSetDao.findList(userLevelSet);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }
}
