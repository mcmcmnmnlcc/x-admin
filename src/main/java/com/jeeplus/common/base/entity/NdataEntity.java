package com.jeeplus.common.base.entity;

import com.jeeplus.modules.sys.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class NdataEntity extends NbaseEntity {

    /**
     * 实体编号（唯一标识）
            */
    protected String id;
    protected String remarks;	// 备注
    protected User createBy;	// 创建者
    protected Date createDate;	// 创建日期
    protected User updateBy;	// 更新者
    protected Date updateDate;	// 更新日期
    protected String delFlag="0"; 	// 删除标记（0：正常；1：删除；2：审核）

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";
}
