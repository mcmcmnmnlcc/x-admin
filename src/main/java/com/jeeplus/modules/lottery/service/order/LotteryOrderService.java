package com.jeeplus.modules.lottery.service.order;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.PageBean;
import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.modules.lottery.dao.order.LotteryOrderDao;
import com.jeeplus.modules.lottery.entity.order.LotteryOrder;
import com.jeeplus.modules.platform.dao.PlatformUserDao;
import com.jeeplus.modules.platform.entity.PlatformUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LotteryOrderService extends BaseService {
    @Autowired
    private PlatformUserDao platformUserDao;
    @Autowired
    private LotteryOrderDao lotteryOrderDao;

    public LotteryOrder get(String id) {
        return lotteryOrderDao.get(id);
    }

    public PageBean<LotteryOrder> findList(PageBean<LotteryOrder> pageBean, LotteryOrder lotteryOrder) {

        //用插件分页(sql自己改写，不能用分页插件,太慢)
        //com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        int startIndex=(pageBean.getPageNo()-1)*pageBean.getPageSize();
        lotteryOrder.setStartIndex(startIndex);
        lotteryOrder.setPageSize(pageBean.getPageSize());
        List<LotteryOrder> list=lotteryOrderDao.findList_v2(lotteryOrder);
        int count=lotteryOrderDao.getCount();
        pageBean.setCount(count);//设置总记录数
        pageBean.setList(list);
        return pageBean;
    }
}
