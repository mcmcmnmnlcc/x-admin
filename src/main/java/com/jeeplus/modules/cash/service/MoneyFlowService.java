package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.cash.dao.MoneyFlowDao;
import com.jeeplus.modules.cash.entity.UserMoneyFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MoneyFlowService extends BaseService {
    @Autowired
    MoneyFlowDao moneyFlowDao;

    public PageBean<UserMoneyFlow> findList(PageBean<UserMoneyFlow> pageBean, UserMoneyFlow userMoneyFlow){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);

        //long startTime=System.currentTimeMillis();
        List<UserMoneyFlow> list=moneyFlowDao.findList(userMoneyFlow);
        //long endTime=System.currentTimeMillis();
        //System.out.println("用时："+(endTime-startTime));

        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }
}
