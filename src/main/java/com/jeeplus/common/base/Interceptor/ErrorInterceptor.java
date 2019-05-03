package com.jeeplus.common.base.Interceptor;

import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ErrorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

        if(response.getStatus()==500){
            Result result=new Result(true, StatusCode.OK,"500");
        }else if(response.getStatus()==404){
            Result result=new Result(true, StatusCode.OK,"无法找到指定资源");

        }
    }

}
