package com.jeeplus.test;

import com.google.gson.Gson;
import com.jeeplus.modules.platform.dto.RechargeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJson {
    @Autowired
    Gson gson;

    @Test
    public void test1(){
        RechargeDTO rechargeDTO=new RechargeDTO();
        rechargeDTO.setMoney("1");
        rechargeDTO.setRemark("我的测试人工充值");
        rechargeDTO.setUserId("qq131403");

        String str=gson.toJson(rechargeDTO);
        System.out.println(str);

    }


}
