package com.jeeplus.modules.platform.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class WindKong extends NdataEntity {
    private String userId;      //用户名
    private Integer winDay;     //盈利天数
    private Integer gameDay;       //游戏天数
    private Double winRate;         //盈利比例
    private Double winLossMoney;       //盈亏金额
    private String userLevel;       //会员层级
    private Long countRukuanMoney;		//'累计入款总额，以千为单位',
    private Long countChukuanMoney;		//'累计出款金额，千为单位',

    private String sDate;  //用beginDate转成的yyyyMMdd格式的字符串
    private String eDate;
    private String sortType;//1-降序 2-升序
    private String sortBy;//根据什么排序

    private String  searchPersonInfo; // 查询人员信息
    private String  searchContext;  //具体的搜索内容

    private String  beginDate;

    private String  endDate;

    private String  agent;

    private String  levelName;

    private String userName;

    private String parentAgent;

}
