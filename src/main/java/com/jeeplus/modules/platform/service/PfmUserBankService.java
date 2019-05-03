package com.jeeplus.modules.platform.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.platform.dao.PfmUserBankDao;
import com.jeeplus.modules.platform.entity.PfmUserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PfmUserBankService extends BaseService {
    @Autowired
    PfmUserBankDao pfmUserBankDao;

    public PageBean<PfmUserBank> findList(PageBean<PfmUserBank> pageBean, PfmUserBank pfmUserBank){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<PfmUserBank> list=pfmUserBankDao.findList(pfmUserBank);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }
}
