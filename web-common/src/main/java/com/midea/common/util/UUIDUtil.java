package com.midea.common.util;

import java.util.UUID;

/**
 * @author chenzewei
 * <br />邮箱： zewei.chen@midea.com
 * <br />描述：UUID工具
 * <br />版本:1.0.0
 * <br />日期： 2015-12-01 下午5:50:10
 */
public class UUIDUtil {

	/**
	 * 生成UUID字符串
	 * @return
	 */
	public static String generateUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
