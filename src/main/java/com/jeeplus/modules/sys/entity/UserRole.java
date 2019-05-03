package com.jeeplus.modules.sys.entity;

import lombok.Data;

@Data
public class UserRole {
    private String userId;
    private String roleId;

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
