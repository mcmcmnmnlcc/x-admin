package com.jeeplus.test.Role;

import com.jeeplus.modules.sys.dao.RoleDao;
import com.jeeplus.modules.sys.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRole {
    @Autowired
    private RoleDao roleDao;

    @Test
    public void test1(){
        List<Role> list= roleDao.getByUserId("7df969056b0c49f1954d9542fda39167");
        System.out.println(list.size());

    }
}
