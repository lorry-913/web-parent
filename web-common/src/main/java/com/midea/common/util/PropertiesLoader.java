package com.midea.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties getProperties(String fileName) {
        Properties moduleMap = new Properties();
        String path = PropertiesLoader.class.getClassLoader().getResource(fileName).getPath();
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            moduleMap.load(is);
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moduleMap;
    }

    public static void main(String[] args) {
        //对于ClassLoader.getResource， 直接调用的就是ClassLoader 类的getResource方法，
        // 那么对于getResource("")，path不以'/'开头时，首先通过双亲委派机制，
        // 使用的逐级向上委托的形式加载的，最后发现双亲没有加载到文件，
        // 最后通过当前类加载classpath根下资源文件。对于getResource("/")，
        // '/'表示Boot ClassLoader中的加载范围，因为这个类加载器是C++实现的，所以加载范围为null。
       Properties moduleMap= PropertiesLoader.getProperties("goods_module_map.properties");//
        String str=moduleMap.getProperty("GetGoodsList");
        Map<String, Object> map = JacksonUtils.readValue(str, Map.class);
//        System.out.println(PropertiesLoader.class.getResource("/goods_module_map.properties"));
    }
}
