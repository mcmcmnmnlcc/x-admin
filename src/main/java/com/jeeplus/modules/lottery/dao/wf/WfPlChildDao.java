package com.jeeplus.modules.lottery.dao.wf;

import com.jeeplus.common.base.dao.CrudDao;
import com.jeeplus.modules.lottery.entity.wf.WfMain;
import com.jeeplus.modules.lottery.entity.wf.WfPlChild;

import java.util.List;

public interface WfPlChildDao {


    /**
     * 插入一个赔率
     * @param wfPlChild
     * @return
     */
    public int insert(WfPlChild wfPlChild);

    /**
     * 根据玩法标志查询所属赔率
     * @param wfFlag
     * @return
     */
    public List<WfPlChild> findListByWfFlag(String wfFlag);

    /**
     * 修改赔率
     * @param wfPlChild
     * @return
     */
    public int update(WfPlChild wfPlChild);


    /**
     * 删除赔率根据赔率id
     * @param wfPlChild
     * @return
     */
    public int deleteById(WfPlChild wfPlChild);


    /**
     * 根据玩法标识删除
     * @param wfFlag
     * @return
     */
    public int deleteByWfFlag(String wfFlag);
}
