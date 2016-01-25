package com.ronaldong.messi.data.entity.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;


/**
 * 分页结果
 *
 * @author Yan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingResult extends Result {
	/**
	 * 返回列表
	 */
	public Collection<? extends Result> array;
	/**
	 * 总页数
	 */
	public int totalPage;

	public PagingResult() {

	}
	
	

}
