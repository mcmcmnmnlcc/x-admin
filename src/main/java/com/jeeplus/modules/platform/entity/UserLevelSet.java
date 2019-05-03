package com.jeeplus.modules.platform.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UserLevelSet extends NdataEntity {
    private String levelName;		// 层级名称
    private String levelDescription;		// 描述
    private Date startTime;		// 起始时间
    private Date endTime;		// 结束时间
    private Double countTouzhuMoney;		// 投注总额
    private Double countRukuanMoney;		// 存款总额
    private Double countDml;		// 打码量
    private Integer countUser;		// 总人数
    private Integer levelSort;		// 排序
    private String	 countTouzhuMoneyStr;// 投注总额用于千分位展示
    private String countRukuanMoneyStr;// 存款总额用于千分位展示

}
