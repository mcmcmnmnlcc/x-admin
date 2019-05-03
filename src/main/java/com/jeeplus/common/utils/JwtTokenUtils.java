package com.jeeplus.common.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecretdemo";

    //一个密匙
    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();


    /**
     * 签发者
     */
    private static final String ISS = "lcc";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     * @param username
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return token;
    }

    //生成一个jwt格式的token
    public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {

        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();
    }

    //解析一个token
    public static Map<String,Object> valid(String token) throws ParseException, JOSEException {

       //解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload=jwsObject.getPayload();

        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);

            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    resultMap.put("Result", 2);
                }
            }
        }else {
            resultMap.put("Result", 1);
        }
        return resultMap;

    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getSuject(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * 判断是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
