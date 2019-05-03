package com.jeeplus.security.service;

import com.google.gson.Gson;
import com.jeeplus.common.utils.RedisUtil;
import com.jeeplus.common.utils.SpringUtils;
import com.jeeplus.modules.sys.dao.UserDao;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.UserService;
import com.jeeplus.security.po.JwtUser;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 密码加密方式
     * @return
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {

        /**
         * 根据参数获取凭证 可以对接数据库 redis等
         * 例如 当username="15512343256"时，openId为"123"
         * 验证时需要有密码规则即需要用户注册时将密码加密插入数据库
         * 举例：
         */
//        String username = "12";
//        String paswword = "";
//        if(username.equals(uname)){
//            paswword = bCryptPasswordEncoder().encode("123");
//        }

        UserService userService= SpringUtils.getBean(UserService.class);
        RedisUtil redisUtil=SpringUtils.getBean(RedisUtil.class);
        Gson gson=SpringUtils.getBean(Gson.class);
        User user=null;
        String str_security_user="security_user:"+uname;
        try {
            boolean b_redis=redisUtil.exists(str_security_user);
            if(b_redis){//如果缓存中有数据则从缓存中取
                String strUser=redisUtil.get(str_security_user);
                user= gson.fromJson(strUser,User.class);
            }else{//如果缓存中没有则从数据库中取,然后添加到缓存
                user=userService.getByUsername(uname);
                redisUtil.set(str_security_user,gson.toJson(user),60);
            }
        }catch (Exception e){
            e.printStackTrace();
            //发生异常了也从数据库中取
            user=userService.getByUsername(uname);
           // redisUtil.set(str_security_user,gson.toJson(user),60);
        }

        //String mpaswword = bCryptPasswordEncoder().encode("123456");
        //user.setPassword(mpaswword);

        if(null==user){
            throw new UsernameNotFoundException("无法查找用户信息");
        }

        //把相关信息放到JwtUser中
        JwtUser userInfo= new JwtUser(user.getLoginName(),user.getPassword());
        userInfo.setUserid(user.getId());//放入用户的id
        Set authoritiesSet = new HashSet();
        List<String> permissionlistList=user.getPermissionList();
        for(String str:permissionlistList){
            if(null==str || "".equals(str.trim())){
                continue;
            }
            String str2="ROLE_"+str;
            GrantedAuthority authority = new SimpleGrantedAuthority(str2);
            authoritiesSet.add(authority);
        }
        //GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");// 模拟从数据库中获取用户角色
        userInfo.setAuthorities(authoritiesSet);
        return userInfo;
    }


}
