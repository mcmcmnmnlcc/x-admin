package com.jeeplus.test.Menu;

import com.google.gson.Gson;
import com.jeeplus.modules.sys.entity.Menu;
import com.jeeplus.modules.sys.entity.Office;
import com.jeeplus.modules.sys.entity.Role;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.MenuService;
import com.jeeplus.modules.sys.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testMenu {

    @Autowired
    MenuService menuService;

    @Autowired
    Gson gson;

    @Test
    public void test1(){
        List<Menu> menuList= menuService.getMenuListByUserid("f4888b08f93243bdb09bce9c168e3826");
       String str=gson.toJson(menuList);
        System.out.println(str);
    }

    @Test
    public void test2(){
        User u=new User();
        Office compan=new Office();
        compan.setId("1");
        //设置公司
        u.setCompany(compan);
        Office office=new Office();
        office.setId("5");
        u.setOffice(office);//设置部门
        u.setLoginName("lcc3");//登陆名
        u.setPassword("123456");//密码
        u.setNo("777");//工号
        u.setName("zhangsan");//姓名
        u.setEmail("m@163.com");
        u.setPhone("05664640");//电话
        u.setMobile("151785466");//手机
        u.setUserType("普通用户");//用户类型
        u.setLoginFlag("1");//1和0，是否允许登陆
        u.setPhoto("/gd");//头像

        //给用户设置2个角色
        Role r1=new Role("883c2b649b8046a2b89692798941ab58");
        Role r2=new Role("e04ff55d39c04d5681e97665f3e45504");
        u.getRoleList().add(r1);
        u.getRoleList().add(r2);
        u.setAgentFlag("代理用户");//所属代理用户

        String str=gson.toJson(u);
        System.out.println(str);
    }
}
