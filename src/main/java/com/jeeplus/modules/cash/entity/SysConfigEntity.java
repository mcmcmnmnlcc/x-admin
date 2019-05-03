package com.jeeplus.modules.cash.entity;

import lombok.Data;

@Data
public class SysConfigEntity {
    private Integer id;
    private String type;
    private String label;
    private String value;
    private String status;
    private String reserved;
}
