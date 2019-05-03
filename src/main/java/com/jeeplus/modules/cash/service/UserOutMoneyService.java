package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.cash.dao.UserOutMoneyFlowDao;
import com.jeeplus.modules.cash.entity.PfmUserChargeWithDraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserOutMoneyService extends BaseService {
    @Autowired
    UserOutMoneyFlowDao userOutMoneyFlowDao;

    /**
     * 根据明细表id查询出款记录
     * @param id
     * @return
     */
    public PfmUserChargeWithDraw getById(String id){
        return userOutMoneyFlowDao.get(id);
    }

    /**
     * 分条件列表查询
     * @param pageBean
     * @param pfmUserChargeWithDraw
     * @return
     */
    public PageBean<PfmUserChargeWithDraw> findList(PageBean<PfmUserChargeWithDraw> pageBean, PfmUserChargeWithDraw pfmUserChargeWithDraw){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<PfmUserChargeWithDraw> list=userOutMoneyFlowDao.findList(pfmUserChargeWithDraw);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }
}
