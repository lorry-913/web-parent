package com.midea.common.config;

/**
 * @author : 陆瑞
 * @version : 1.0.0
 * @date : 2020-06-05 02:36
 **/
public class JwtUtil {
    public static String secret;

    public static String issue;

    public static void setToke(TokenSetting toke){
        secret=toke.secret;
        issue =toke.issue;
    }

    public static String getToken(){
        return secret+issue;
    }
}
