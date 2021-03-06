package com.midea.common.utils;

import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {

	/**
	 * 通过图片的url获取图片的base64字符串
	 *
	 * @param imgUrl
	 *            图片url
	 * @return 返回图片base64的字符串
	 */
	public static String getBase64ByImgUrl(String imgUrl) {

		URL url = null;

		InputStream is = null;

		ByteArrayOutputStream outStream = null;

		HttpURLConnection httpUrl = null;

		try {

			url = new URL(imgUrl);

			httpUrl = (HttpURLConnection) url.openConnection();

			httpUrl.connect();

			httpUrl.getInputStream();

			is = httpUrl.getInputStream();

			outStream = new ByteArrayOutputStream();

			// 创建一个Buffer字符串

			byte[] buffer = new byte[1024];

			// 每次读取的字符串长度，如果为-1，代表全部读取完毕

			int len = 0;

			// 使用一个输入流从buffer里把数据读取出来

			while ((len = is.read(buffer)) != -1) {

				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度

				outStream.write(buffer, 0, len);

			}

			// 对字节数组Base64编码

			return Base64Utils.encodeToString(outStream.toByteArray());

		} catch (Exception e) {

			e.printStackTrace();

		}

		finally {

			if (is != null)

			{

				try {

					is.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

			if (outStream != null)

			{

				try {

					outStream.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

			if (httpUrl != null)

			{

				httpUrl.disconnect();

			}

		}

		return imgUrl;

	}

	public static void main(String[] args) {
		System.out.println(getBase64ByImgUrl(
				"https://midea-images.oss-cn-hangzhou.aliyuncs.com/api_oss/20180410182627532379/20180926/1537949752789.jpg"));
	}
}
