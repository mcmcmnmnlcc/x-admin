package com.jeeplus.modules.cash.service;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.cash.dao.ThirdBankAccountDao;
import com.jeeplus.modules.cash.entity.ThirdAccountNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ThirdBankAccountService extends BaseService {
    @Autowired
    ThirdBankAccountDao thirdBankAccountDao;


    public PageBean<ThirdAccountNo> findList(PageBean<ThirdAccountNo> pageBean, ThirdAccountNo thirdAccountNo){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<ThirdAccountNo> list=thirdBankAccountDao.findList(thirdAccountNo);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }
}
