package com.jeeplus.test;

import com.jeeplus.common.utils.JwtTokenUtils;
import com.nimbusds.jose.JOSEException;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    @Test
    public void testToken(){
        String p="mcmcm";
        String b=JwtTokenUtils.createToken(p,false);
        System.out.println("token="+b);

        String c=JwtTokenUtils.getSuject(b);
        System.out.println("解析："+c);
    }

    //生成token的业务逻辑
    public static String TokenTest(String uid) {
        //获取生成token

        Map<String, Object> map = new HashMap<>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+10000);
        try {
            String token = JwtTokenUtils.creatToken(map);
            System.out.println("token="+token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;

    }

    //处理解析的业务逻辑
    public static void ValidToken(String token) {
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = JwtTokenUtils.valid(token);
                int i = (int) validMap.get("Result");
                if (i == 0) {
                    System.out.println("token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    System.out.println("uid是" + jsonObject.get("uid"));
                    System.out.println("sta是"+jsonObject.get("sta"));
                    System.out.println("exp是"+jsonObject.get("exp"));
                } else if (i == 2) {
                    System.out.println("token已经过期");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        //获取token
        String uid = "kkksuejrmf";
        String token = TokenTest(uid);
        System.out.println("token->"+token);
        //解析token
        ValidToken(token);
    }
}
