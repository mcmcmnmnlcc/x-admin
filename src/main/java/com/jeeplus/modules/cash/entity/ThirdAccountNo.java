package com.jeeplus.modules.cash.entity;

import com.jeeplus.common.base.entity.NdataEntity;
import lombok.Data;

@Data
public class ThirdAccountNo extends NdataEntity {

    /**
     * 渠道名称
     */
    private String channelName;
    private String merchantNo;			//商户号
    private String channelFlag;         // 充值渠道
    private String secretKey;           //商户秘钥
    private Integer sort;          	  // 排序
    private String status;              //0:禁用 1:启用
    private String requestUrl;          // 充值调用地址
    private String requestUrl2;           // 特殊调用地址
    private String notifyUrl;           // 第三方通知我们的地址
}
