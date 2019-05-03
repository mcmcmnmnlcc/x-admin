package com.jeeplus.security.filter;

import com.google.gson.Gson;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.JwtTokenUtils;
import com.jeeplus.common.utils.PrinterUtils;
import com.jeeplus.common.utils.SpringUtils;
import com.jeeplus.security.po.ClientUserEntity;
import com.jeeplus.security.po.JwtUser;

import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    /**
     *接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        //从输入流中获取登录信息
        System.out.println("尝试登录");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        JwtUser jwtUser= (JwtUser) authResult.getPrincipal();
        ClientUserEntity clientUserEntity=new ClientUserEntity();
        clientUserEntity.setUsername(jwtUser.getUsername());
        clientUserEntity.setTokenid(jwtUser.getUsername()+":1");
        clientUserEntity.setId(jwtUser.getUserid());
        Gson gson= (Gson) SpringUtils.getBean("gson");
        String strJwt=gson.toJson(clientUserEntity);
        String token = JwtTokenUtils.createToken(strJwt,false);
        logger.debug("登陆成功,用户名:"+jwtUser.getUsername()+",token->"+JwtTokenUtils.TOKEN_PREFIX+token);

        //向头写入token
        response.addHeader(JwtTokenUtils.TOKEN_HEADER,JwtTokenUtils.TOKEN_PREFIX+token);
        Result result=new Result(false, StatusCode.LONGIN_SUCCESS,"登陆成功");
        String str=gson.toJson(result);
        PrinterUtils.print(response,str);

    }

    /**
     * 这是验证失败时候调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
       logger.debug("登陆失败");

        Result result=new Result(false, StatusCode.LOGINERROR,"登陆失败，用户名或密码错误");
        Gson gson= (Gson) SpringUtils.getBean("gson");
        String str=gson.toJson(result);
        PrinterUtils.print(response,str);
    }


}
