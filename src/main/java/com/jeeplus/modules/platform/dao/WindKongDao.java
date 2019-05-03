package com.jeeplus.modules.platform.dao;

import com.jeeplus.modules.platform.entity.WindKong;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WindKongDao {

    /**
     * 查询用户的盈亏金额，可以带条件，比如用户id，开始日期和结束日期
     * @param WindKong
     * @return
     */
    public Map getUserDayFlowCount(WindKong WindKong);

    /**
     * 我估计是查询指定时间段金额有变化的用户，可以带条件，开始时间和结束时间
     * @param WindKong
     * @return
     */
    List<String> getTodayUserList(WindKong WindKong);

    /**
     * 估计是查询用户以天为单位的盈亏金额列表，可以带入条件，开始时间和结束时间
     * @param uid
     * @return
     */
    List<Integer> getGameDay(String uid);

    /**
     * 查询某个会员当天的盈亏金额
     * @param user_id
     * @return
     */
    List<Double>  getTdWinlossMoney(String user_id);
    /**
     * 查询某个会员当天的有效投注额
     * @param user_id
     * @return
     */
    List<BigInteger> getTdBetMoney(String user_id);

    /**
     * 查询某个用户在哪些天进行了游戏（投注）
     * @param user_id
     * @return
     */
    List<String> getV2GameDay(String user_id, Date beginDate, Data endDate);

    /**
     * 查询风控信息，可以带条件
     * @param windKong
     * @return
     */
    List<WindKong> findList(WindKong windKong);



}
