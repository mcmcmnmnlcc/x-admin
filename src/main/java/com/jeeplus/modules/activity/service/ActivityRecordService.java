package com.jeeplus.modules.activity.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.modules.activity.dao.ActivityRecordDao;
import com.jeeplus.modules.activity.entity.ActivityRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityRecordService {
    @Autowired
    ActivityRecordDao activityRecordDao;

    /**
     * 查询活动记录列表
     * @param pageBean
     * @param activityRecord
     * @return
     */
    public PageBean<ActivityRecord> findList(PageBean<ActivityRecord> pageBean, ActivityRecord activityRecord){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<ActivityRecord> list=activityRecordDao.findList(activityRecord);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }
}
