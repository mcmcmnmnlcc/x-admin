package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.cash.dao.UserChargeGateTypeDao;
import com.jeeplus.modules.cash.entity.UserChargeGateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 在线充值网关管理
 */
@Service
@Transactional
public class UserChargeGateTypeService extends BaseService {
    @Autowired
    UserChargeGateTypeDao userChargeGateTypeDao;

    public PageBean<UserChargeGateType> findList(PageBean<UserChargeGateType> pageBean, UserChargeGateType userChargeGateType){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<UserChargeGateType> list=userChargeGateTypeDao.findList(userChargeGateType);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }
}
