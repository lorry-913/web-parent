package com.midea.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //token有效时间 一天
    private static long DEAD_TIME=1440*60*1000;
    //秘钥生成uuid 确定唯一性
    private static String  tokenSecRet;

    /**
     * json web token 生成token，用户退出后消失
     *
     *
     * 一个token分3部分，按顺序为
     *
     * 头部（header)
     * 其为载荷（payload)
     * 签证（signature)
     * 由三部分生成token
     *
     * @param userName
     * @param pwd
     * @return
     */
    public static String sign(String userName,String pwd){
        try {
            //设置过期时间
            Date date=new Date(System.currentTimeMillis()+DEAD_TIME);
            //token私钥加密
            Algorithm algorithm=Algorithm.HMAC256(tokenSecRet);
            //设置头部信息
            Map<String,Object> map=new HashMap<>(2);
            map.put("type","JWT");//类型JWT 算法HS256
            map.put("encryption","HS256");
            long date1=new Date().getTime();
            //返回带有用户信息的签名
            return JWT.create().withHeader(map)
                    .withClaim("userName",userName)
                    .withClaim("pwd",pwd)
                    .withClaim("Time",date1)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证token是否正确
     * @param token
     * @return
     */
    public static  boolean tokenVerify(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC256(tokenSecRet);
            JWTVerifier verifier=JWT.require(algorithm).build();
            //验证
            DecodedJWT decodedJWT=verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取登陆用户token中的用户ID
     * @param token
     * @return
     */
    public static int getUserID(String token){
        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("userId").asInt();
    }

}
