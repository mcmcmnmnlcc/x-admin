package com.jeeplus.modules.platform.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

import java.util.Date;

@Data
public class OperationLog extends NdataEntity {
    private String userId;		// 用户名
    private String optModular;		// 操作模块
    private String optAdmin;		// 操作者
    private String status;		// 结果
    private String returnMsg;		// 回执信息
    private Date createTime;		// 操作时间
    private String optIp;		// IP
    private String content;		// 日志内容
    private Date beginCreateTime;		// 开始 操作时间
    private Date endCreateTime;		// 结束 操作时间
}
