package com.midea.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.util.JSONPObject;


import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.text.DecimalFormat;
import java.util.*;

public class HttpClientUtils {

	public static String post(String url, Map<String, Object> paramMap) {
		try {
			CloseableHttpClient httpclient = createIgnoreVerifySSL();
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> formParams = mapToList(paramMap);
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String get(String url, Map<String, Object> paramMap) {
		try {
			String param = getUrlParamsByMap(paramMap);
			CloseableHttpClient httpclient = createIgnoreVerifySSL();
			HttpGet httpget = new HttpGet(url + "?" + param);
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<NameValuePair> mapToList(Map<String, Object> paramMap) throws Exception {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), toStr(entry.getValue())));
		}
		return formParams;
	}

	public static Map<String, String> parseParam(String params) throws Exception {
		if (isEmpty(params)) {
			return null;
		}
		List<NameValuePair> list = URLEncodedUtils.parse(params, Charset.forName("UTF-8"));
		if (isEmpty(list)) {
			return null;
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		for (NameValuePair pair : list) {
			paramMap.put(pair.getName(), pair.getValue());
		}
		return paramMap;
	}

	/**
	 * 将url参数转换成map
	 *
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 *
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) throws Exception {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), "utf-8"));
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	public static void main(String[] args) {
	}

	private static DecimalFormat DecimalFormatter = new DecimalFormat("#.######");

	public static String toStr(Object value) {
		if (null == value) {
			return "";
		}
		if (((value instanceof Double)) || ((value instanceof Float))) {
			return DecimalFormatter.format(value);
		}
		return value.toString().trim();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if ((obj instanceof String)) {
			return ((String) obj).trim().length() == 0;
		}
		if ((obj instanceof Collection)) {
			return ((Collection) (Collection) obj).size() == 0;
		}
		if ((obj instanceof Object[])) {
			return ((Object[]) (Object[]) obj).length == 0;
		}
		return false;
	}

	public static CloseableHttpClient createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");
		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		sc.init(null, new TrustManager[] { trustManager }, null);
		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sc)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);
		// 创建自定义的httpclient对象
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
				.setConnectionManager(connManager).build();
		return client;
	}

	public static String postJson(String url, JSONPObject para) {
		try {
			StringEntity s = new StringEntity(para.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postXml(String url, String para) {
		try {
			StringEntity s = new StringEntity(para);
			s.setContentEncoding("UTF-8");
			s.setContentType("text/xml");
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(s);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postXmlSsl(String url, String para, String key, String path) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(path));// 放退款证书的路径
			try {
				keyStore.load(instream, key.toCharArray());
			} finally {
				instream.close();
			}
			// 实例化密钥库 & 初始化密钥工厂
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, key.toCharArray());

			// 创建 SSLContext
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
					null, new DefaultHostnameVerifier());

			BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build(),
					null, null, null);

			StringEntity s = new StringEntity(para);
			s.setContentEncoding("UTF-8");
			s.setContentType("text/xml");
			HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connManager).build();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(s);
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String postHeader(String url, Map<String, Object> paramMap, Map<String, String> headers) {
		try {
			CloseableHttpClient httpclient = createIgnoreVerifySSL();
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> formParams = mapToList(paramMap);
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httppost.setEntity(uefEntity);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httppost.setHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postHeader(String url, String jsonParam, Map<String, String> headers) {
		try {
			CloseableHttpClient httpclient = createIgnoreVerifySSL();
			HttpPost httppost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httppost.setEntity(entity);
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httppost.setHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				String responseStr = EntityUtils.toString(entity, "UTF-8");
				return responseStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] protobufPost(InputStream inputStream, String url) {
		try {
			CloseableHttpClient httpclient = createIgnoreVerifySSL();
			HttpPost httppost = new HttpPost(url);

			InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream);// 解决中文乱码问题
			inputStreamEntity.setContentEncoding("UTF-8");
			inputStreamEntity.setContentType("x-protobuf");
			httppost.setEntity(inputStreamEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// String responseStr = EntityUtils.toString(entity, "UTF-8");
				byte[] responseStr = EntityUtils.toByteArray(entity);
				return responseStr;
			} else {
				return "no response".getBytes();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error".getBytes();
		}
	}
}
