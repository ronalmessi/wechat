package com.ronaldong.messi.data.entity.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
		@JsonSubTypes.Type(value = AndroidVersionResult.class, name = "AndroidVersionResult"),
		@JsonSubTypes.Type(value = PersonResult.class, name = "PersonResult"),
		@JsonSubTypes.Type(value = IncrementalUpdateVO.class, name = "IncrementalUpdateVO"),
		@JsonSubTypes.Type(value = MessageResult.class, name = "MessageResult"),
		@JsonSubTypes.Type(value = PagingResult.class, name = "PagingResult"),
		@JsonSubTypes.Type(value = SimpleGroupTagResult.class, name = "SimpleGroupTagResult"),
		 })
public class Result {

	public Result() {

	}
}
