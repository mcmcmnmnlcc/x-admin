package com.jeeplus.security.filter;


import com.google.gson.Gson;
import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import com.jeeplus.common.utils.JwtTokenUtils;
import com.jeeplus.common.utils.PrinterUtils;
import com.jeeplus.common.utils.SpringUtils;
import com.jeeplus.common.utils.ThreadAttributes;
import com.jeeplus.security.po.ClientUserEntity;
import com.jeeplus.security.po.JwtUser;
import com.jeeplus.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:aha
 * @Description:
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取请求头信息，头key是"Authorization"
        String header = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        // 如果请求头中没有Authorization信息则直接放行了，实际环境中除了login必须携带令牌访问
        if(header == null || !header.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
            String url=request.getRequestURI();
            System.out.println("请求的url="+url);
//            Result result=new Result(false, StatusCode.TOKEN_ERROR,"请携带令牌");
//            Gson gson=new Gson();
//            String str=gson.toJson(result);
//            PrinterUtils.print(response,str);
            chain.doFilter(request,response);

            return;
        }

        try{
            // 如果请求头中有token，则进行解析，并且设置认证信息
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header,response);

            if(null==authenticationToken){
                Result result=new Result(false, StatusCode.TOKEN_ERROR,"令牌解析错误");
                Gson gson=new Gson();
                String str=gson.toJson(result);
                PrinterUtils.print(response,str);
                return;
            }
            logger.debug("令牌认证通过");

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);
        }catch (io.jsonwebtoken.ExpiredJwtException e){
            logger.debug("令牌超时:"+e.getMessage());
            Result result=new Result(false, StatusCode.TOKEN_ERROR,"令牌超时");
            Gson gson=new Gson();
            String str=gson.toJson(result);
            PrinterUtils.print(response,str);
            return;

        }catch (io.jsonwebtoken.SignatureException e){
            //e.printStackTrace();
            logger.debug("令牌错误:"+e.getMessage());
            Result result=new Result(false, StatusCode.TOKEN_ERROR,"令牌解析错误");
            Gson gson=new Gson();
            String str=gson.toJson(result);
            PrinterUtils.print(response,str);
            return;
        }

    }

    /**
     *这里从token中获取用户信息并新建一个token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String header,HttpServletResponse response) {


        String token = header.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        //解析token
        String principal = JwtTokenUtils.getSuject(token);

        Gson gson= (Gson) SpringUtils.getBean("gson");
        ClientUserEntity clientUserEntity=gson.fromJson(principal,ClientUserEntity.class);
        UserDetailsServiceImpl userDetailsService=SpringUtils.getBean(UserDetailsServiceImpl.class);

        UserDetails userDetails = userDetailsService.loadUserByUsername(clientUserEntity.getUsername());


        //放到这里面，准备后面再controller里面取
        ThreadAttributes.setThreadAttribute("username",clientUserEntity.getUsername());
        ThreadAttributes.setThreadAttribute("userid",clientUserEntity.getId());

        if (principal != null) {
            //验证通过回写token，否则时间就了会过期
            String strJwt=gson.toJson(clientUserEntity);
            String token2 = JwtTokenUtils.createToken(strJwt,false);
            //向头写入token
            response.addHeader(JwtTokenUtils.TOKEN_HEADER,JwtTokenUtils.TOKEN_PREFIX+token2);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        return null;
    }



}
