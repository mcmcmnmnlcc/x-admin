package com.jeeplus.modules.lottery.service.types;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.lottery.dao.types.LotteryTypeDao;
import com.jeeplus.modules.lottery.entity.types.LotteryTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LotteryTypeService extends BaseService {
    @Autowired
    LotteryTypeDao lotteryTypeDao;

    /**
     * 查询彩票类型，这里的类型是从字典库中取的
     * @param pageBean
     * @param lotteryTypeEntity
     * @return
     */
    public PageBean<LotteryTypeEntity> findList(PageBean<LotteryTypeEntity> pageBean, LotteryTypeEntity lotteryTypeEntity){
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        List<LotteryTypeEntity> list=lotteryTypeDao.findList(lotteryTypeEntity);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }
}

