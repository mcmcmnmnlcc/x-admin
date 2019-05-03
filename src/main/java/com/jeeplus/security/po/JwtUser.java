package com.jeeplus.security.po;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class JwtUser implements UserDetails {
    private String username;
    private String password;
    private String userid;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 自定义值
     * @param username
     * @param password
     */
    public JwtUser(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     *
     * @return 获取权限信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否未过期，默认是false
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否未锁定，默认是false
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号凭证是否未过期，默认是false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 默认也是false
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
