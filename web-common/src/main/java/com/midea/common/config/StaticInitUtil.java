package com.midea.common.config;

import com.midea.common.config.TokenSetting;
import org.springframework.stereotype.Component;

/**
 * @author : 陆瑞
 * @version : 1.0.0
 * @date : 2020-06-05 02:30
 *
 * 静态代理类 spring启动后StaticInitUtil被注入，注入必运行构造函数
 **/
@Component
public class StaticInitUtil {
    public TokenSetting tokenSetting;

    public StaticInitUtil(TokenSetting tokenSetting){
        JwtUtil.setToke(tokenSetting);
    }
}
