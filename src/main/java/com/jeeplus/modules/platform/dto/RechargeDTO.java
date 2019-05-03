package com.jeeplus.modules.platform.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用于充值，接受请求数据
 */
@Data
public class RechargeDTO {
    private String userId;
    private String money;
    private String remark;

    @NotBlank(message="打码量不能为空")
    private String chuk_dml;

}
