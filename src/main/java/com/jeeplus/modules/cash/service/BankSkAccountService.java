package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.cash.dao.BankSkAccountDao;
import com.jeeplus.modules.cash.entity.BankSkAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankSkAccountService extends BaseService {
    @Autowired
    BankSkAccountDao bankSkAccountDao;

    public PageBean<BankSkAccount> findList(PageBean<BankSkAccount> pageBean, BankSkAccount bankSkAccount){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<BankSkAccount> list=bankSkAccountDao.findList(bankSkAccount);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;

    }


}
