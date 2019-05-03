package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.modules.platform.dao.PfmUserLoginInfoDao;
import com.jeeplus.modules.platform.entity.PlatformUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PfmUserLoginInfoService {
    @Autowired
    PfmUserLoginInfoDao pfmUserLoginInfoDao;

    public PageBean<PlatformUser> findList(PageBean<PlatformUser> pageBean, PlatformUser platformUser){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<PlatformUser> list=pfmUserLoginInfoDao.findList(platformUser);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }

}
