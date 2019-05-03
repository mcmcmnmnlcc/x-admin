package com.jeeplus.modules.lottery.dao.wf;

import com.jeeplus.common.base.dao.CrudDao;
import com.jeeplus.modules.lottery.entity.wf.WfMain;

import java.util.List;

public interface WfMainDao {

    /**
     * 玩法查询(根据多条件分页)
     * @param wfMain
     * @return
     */
    public List<WfMain> findList(WfMain wfMain);

    /**
     *根据玩法id查询
     * @param id
     * @return
     */
    public WfMain get(String id);

    /**
     *添加玩法
     * @param wfMain
     * @return
     */
    public int insert(WfMain wfMain);

    /**
     * 修改玩法
     * @param wfMain
     * @return
     */
    public int update(WfMain wfMain);

    /**
     *  删除玩法根据id
     * @return
     */
    public int delete(WfMain wfMain);
}
