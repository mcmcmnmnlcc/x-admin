package com.jeeplus.modules.lottery.dao.group;

import com.jeeplus.modules.lottery.entity.group.LotteryWfGroup;
import com.jeeplus.modules.lottery.entity.wf.WfMain;

import java.util.List;

/**
 * 玩法群组DAO接口
 * @author duke
 * @version 2017-03-29
 */

public interface LotteryWfGroupDao {

    /**
     *根据组id查询
     * @param id
     * @return
     */
    public LotteryWfGroup get(String id);


    /**
     * 多条件查询
     * @param lotteryWfGroup
     * @return
     */
    public List<LotteryWfGroup> findList(LotteryWfGroup lotteryWfGroup);

    /**
     * 添加玩法组
     * @param lotteryWfGroup
     * @return
     */
    public int insert(LotteryWfGroup lotteryWfGroup);

    /**
     * 更新玩法组
     * @param lotteryWfGroup
     * @return
     */
    public int update(LotteryWfGroup lotteryWfGroup);

    public int delete(String id);
	
}