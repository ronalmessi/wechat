package com.ronaldong.messi.data.entity.remote;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 网络请求结果vo
 * 
 * @author Yan
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultVO {
	/**
	 * 返回状态：“true”表示成功；“false”表示失败
	 */
	public String status;
	/**
	 * 返回状态码
	 */
	public int statusCode;
	/**
	 * 出错信息
	 */
	public String msg;
	/**
	 * 结果
	 */
	public Result result;

	public long systemTime;
	public ResultVO() {
	}

	@Override
	public String toString() {
		return "ResultVO [status=" + status + ", statusCode=" + statusCode
				+ ", msg=" + msg + ", result=" + result + ", systemTime="
				+ systemTime + "]";
	}

}
