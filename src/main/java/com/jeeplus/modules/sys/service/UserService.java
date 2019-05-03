package com.jeeplus.modules.sys.service;

import com.jeeplus.common.base.service.BaseService;
import com.jeeplus.common.utils.IdGen;
import com.jeeplus.modules.sys.dao.RoleDao;
import com.jeeplus.modules.sys.dao.UserDao;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService extends BaseService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 根据用户id查询一个用户
     * @param id
     * @return
     */
    public User getById(String id){
        User user=userDao.getById(id);
        //根据用户id查询出该用户拥有的角色
        List<Role> list= roleDao.getByUserId(id);
        user.setRoleList(list);

        return user;
    }

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    public User getByUsername(String name){
        User user=userDao.getByLoginName(name);
        //根据用户id查询出该用户拥有的权限标识
        List<String> permissionlist= userDao.getPermissionByUsername(name);
        user.setPermissionList(permissionlist);
        return user;
    }

    /**
     * 添加一个用户
     * @param user
     */
    public void saveUser(User user) {
        //插入用户数据--------------------------------------start
        user.setId(IdGen.uuid());
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        String password=user.getPassword();
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodePwd= passwordEncoder.encode(password);
        user.setPassword(encodePwd);
        userDao.insert(user);
        //插入用户数据--------------------------------------end

        //插入用户对应的角色信息--------------------------------------start
        List<UserRole> rolesList=new ArrayList<>();
        for(Role role : user.getRoleList()){
            UserRole userRole=new UserRole(user.getId(),role.getId());
            rolesList.add(userRole);
        }
        userDao.insertRoles(rolesList);
        //插入用户对应的角色信息--------------------------------------end

    }

    /**
     * 根据用户id删除用户
     * @param userId
     */
    public void deleteUser(String userId){
        //删除用户信息
        userDao.delete(userId);
        //删除用户关联的角色信息
        userDao.deleteUserRole(userId);

    }

    /**
     * 修改用户信息
     * @param user
     */
    public void updateUser(User user){
        user.setUpdateDate(new Date());
        userDao.update(user);
        //删除用户关联的角色信息
        userDao.deleteUserRole(user.getId());
        //插入用户对应的角色信息--------------------------------------start
        List<UserRole> rolesList=new ArrayList<>();
        for(Role role : user.getRoleList()){
            UserRole userRole=new UserRole(user.getId(),role.getId());
            rolesList.add(userRole);
        }
        userDao.insertRoles(rolesList);
        //插入用户对应的角色信息--------------------------------------end

    }


}
