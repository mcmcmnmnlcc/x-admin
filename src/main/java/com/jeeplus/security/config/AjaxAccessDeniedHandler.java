package com.jeeplus.security.config;

import com.google.gson.Gson;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        System.out.println("无权访问");
        response.setContentType("application/json;charset=utf-8");
        Result result=new Result(false, StatusCode.ACCESSERROR,"权限不足");
        Gson gson=new Gson();
        String str=gson.toJson(result);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(str);
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }

    }

}
