package com.ronaldong.messi.data.entity.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;



public class IncrementalUpdateVO extends Result {
	@JsonProperty("1")
	public List<Update> list1;
	@JsonProperty("2")
	public List<Update> list2;
	@JsonProperty("3")
	public List<Update> list3;
	@JsonProperty("4")
	public List<Update> list4;
	@JsonProperty("5")
	public List<Update> list5;
	@JsonProperty("6")
	public List<Update> list6;
	@JsonProperty("7")
	public List<Update> list7;
	@JsonProperty("8")
	public List<Update> list8;
}