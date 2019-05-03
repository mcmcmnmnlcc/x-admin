package com.jeeplus.modules.lottery.service.wf;

import com.github.pagehelper.PageHelper;
import com.jeeplus.common.base.Page;
import com.jeeplus.common.base.PageBean;
;
import com.jeeplus.modules.lottery.dao.wf.WfMainDao;
import com.jeeplus.modules.lottery.dao.wf.WfPlChildDao;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.lottery.entity.wf.WfPlChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WfMainService {
    @Autowired
    WfMainDao wfMainDao;

    @Autowired
    WfPlChildDao wfPlChildDao;


    /**
     * 分页多条件查询玩法列表
     * @param pageBean
     * @param wfMain
     * @return
     */
    //@TargetDataSource(name = "ds1") 这个注解可以切换数据源
    public PageBean<WfMain> findPage(PageBean<WfMain> pageBean, WfMain wfMain) {
        //用插件分页
        com.github.pagehelper.Page p= PageHelper.startPage(pageBean.getPageNo(), pageBean.getPageSize(),true);
        //查询玩法返回列表时不要去查每个玩法的相关赔率，影响效率，待查询单个玩法信息的时候再返回赔率
        List<WfMain> list=wfMainDao.findList(wfMain);
        pageBean.setCount(p.getTotal());//设置总记录数
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 根据玩法id查询玩法
     * @param id
     * @return
     */
    public WfMain getById(String id){
        WfMain wfMain=wfMainDao.get(id);
        //查询该玩法的赔率
        List<WfPlChild> list= wfPlChildDao.findListByWfFlag(wfMain.getWfFlag());
        wfMain.setWfPlChildList(list);
        return wfMain;
    }

    /**
     * 新增玩法
     * @param wfMain
     * @return
     */
    public int save(WfMain wfMain){
        if(null!=wfMain.getWfPlChildList() ){
            for (WfPlChild wfPlChild : wfMain.getWfPlChildList()){
                //wfPlChild.setAwardMoneyInt(LotteryUtils.formatLongValue(wfPlChild.getAwardMoney()+""));
                //	wfPlChild.setRebateConvertNumInt(LotteryUtils.formatDouble(wfPlChild.getRebateConvertNum()+""));
                wfPlChild.setCreateDate(new Date());
                wfPlChild.setUpdateDate(new Date());
                wfPlChild.setWfMain(wfMain);//所属玩法，靠wf_flag依赖关系
                wfPlChild.setDelFlag(wfPlChild.DEL_FLAG_NORMAL);//新增的时候 设置状态为正常
                wfPlChildDao.insert(wfPlChild);
            }
        }
        wfMain.setCreateDate(new Date());
        wfMain.setUpdateDate(new Date());
        return wfMainDao.insert(wfMain);

    }

    public int update(WfMain wfMain){
        if(null!=wfMain.getWfPlChildList() ){
            for (WfPlChild wfPlChild : wfMain.getWfPlChildList()){
                //wfPlChild.setAwardMoneyInt(LotteryUtils.formatLongValue(wfPlChild.getAwardMoney()+""));
                //	wfPlChild.setRebateConvertNumInt(LotteryUtils.formatDouble(wfPlChild.getRebateConvertNum()+""));
                wfPlChild.setWfMain(wfMain);//所属玩法，靠wf_flag依赖关系

                if(null==wfPlChild.getDelFlag()){
                    wfPlChild.setUpdateDate(new Date());
                    wfPlChildDao.update(wfPlChild);
                }else if(wfPlChild.getDelFlag().equals(wfPlChild.DEL_FLAG_NORMAL)){//如果赔率是正常标志，执行更新操作
                    wfPlChild.setUpdateDate(new Date());
                    wfPlChildDao.update(wfPlChild);
                }else if(wfPlChild.getDelFlag().equals(wfPlChild.DEL_FLAG_AUDIT)){//如果赔率是审核标志，执行插入操作
                    wfPlChild.setCreateDate(new Date());
                    wfPlChild.setUpdateDate(new Date());
                    wfPlChildDao.insert(wfPlChild);
                }else if(wfPlChild.getDelFlag().equals(wfPlChild.DEL_FLAG_DELETE)){//如果赔率是删除标志，执行删除操作
                    wfPlChildDao.deleteById(wfPlChild);
                }
            }
        }
        wfMain.setUpdateDate(new Date());
        return wfMainDao.update(wfMain);
    }

    /**
     * 根据玩法id删除一个玩法
     * @param id
     */
    public void delete(String id){
        WfMain wfMain= wfMainDao.get(id);
        wfPlChildDao.deleteByWfFlag(wfMain.getWfFlag());//删除所属玩法的赔率
        wfMainDao.delete(wfMain);//删除玩法

    }
}
