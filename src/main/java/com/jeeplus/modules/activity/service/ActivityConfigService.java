package com.jeeplus.modules.activity.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.activity.dao.ActivityConfigDao;
import com.jeeplus.modules.activity.dao.ActivityDao;
import com.jeeplus.modules.activity.entity.ActivityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityConfigService extends BaseService {
    @Autowired
    ActivityConfigDao activityConfigDao;

    public PageBean<ActivityConfig> findList(PageBean<ActivityConfig> pageBean, ActivityConfig activityConfig){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<ActivityConfig> list=activityConfigDao.findList(activityConfig);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }

}
