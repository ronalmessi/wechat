package com.ronaldong.messi.data.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.ronaldong.messi.data.entity.remote.ResultVO;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by ronaldong on 2015/12/21.
 */
final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectReader  adapter;


    JacksonResponseBodyConverter(ObjectReader adapter) {
        this.adapter = adapter;

    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String resultJson=value.string();
        System.out.println(resultJson);
        ObjectMapper objectMapper=new ObjectMapper();
        ResultVO vo=objectMapper.readValue(resultJson,ResultVO.class);
        return (T)vo;
    }
}