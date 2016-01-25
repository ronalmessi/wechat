package com.ronaldong.messi.data.entity.remote;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AndroidVersionResult extends Result {
	public String versionCode = "1";
	public String versionName = "0.0.1";
	public String downloadUrl = "http://wx.iclass.cn/";
	public String updateIntro = "";
	public String isForcedUpdate = "0";

	public AndroidVersionResult() {

	}

}
