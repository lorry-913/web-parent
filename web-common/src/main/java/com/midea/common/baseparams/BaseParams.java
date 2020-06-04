package com.midea.common.baseparams;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BaseParams implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "appid不能为空")
	private String appid;
	@NotNull(message = "secret不能为空")
	private String secret;

	private String client_ip = "127.0.0.1";
	private String in_or_out = "0"; // 0 内部  1 外部

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getIn_or_out() {
		return in_or_out;
	}

	public void setIn_or_out(String in_or_out) {
		this.in_or_out = in_or_out;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
}
