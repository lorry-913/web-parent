package com.midea.temp.controller;

import com.midea.common.utils.HttpClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 陆瑞
 * @version : 1.0.0
 * @date : 2020-06-05 01:27
 **/
@RestController
public class CommonController {
    @GetMapping(value = "/getRes")
    public String getRes(){
        return "nima";
    }
}
