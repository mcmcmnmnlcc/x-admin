package com.jeeplus.modules.lottery.entity.types;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class LotteryTypeEntity extends NdataEntity {
    private String value;	// 数据值
    private String label;	// 标签名
    private String type;	// 类型
    private String description;// 描述
    private Integer sort;	// 排序
    private String parentId;//父Id
    private String reserved;	//是否有子菜单
    private String reserved2;	//图片地址
    private String reserved3;	//保留与
    private Integer status;  //状态

}
