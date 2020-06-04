package com.midea.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 陆瑞
 * @version : 1.0.0
 * @date : 2020-06-05 02:13
 **/
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class TokenSetting {
    public String secret;

    public String issue;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
