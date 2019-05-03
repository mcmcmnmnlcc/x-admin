package com.jeeplus.security.config;

import com.google.gson.Gson;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.PrinterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint,
        InitializingBean {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Gson gson;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {

        Result result=new Result(false, StatusCode.LONGIN_TIMEOUT,"登陆超时或未携带令牌，请重新登陆");
        String str=gson.toJson(result);
        PrinterUtils.print(response,str);

    }
}
