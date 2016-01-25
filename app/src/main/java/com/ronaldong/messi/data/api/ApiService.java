package com.ronaldong.messi.data.api;

import com.ronaldong.messi.app.Constants;
import com.ronaldong.messi.data.entity.remote.ResultVO;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;


public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResultVO> login(@Field("loginId") String loginId, @Field("password") String password, @Field("deviceToken") String deviceToken, @Field("deviceType") String deviceType);

    @POST("logout")
    Call<ResultVO> logout(@Header("accessToken") String accessToken);


    @POST("validToken")
    Call<ResultVO> validToken(@Header("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("registPushToken")
    Call<ResultVO> registPushToken(@Header("accessToken") String accessToken, @Field("pushToken") String pushToken);

    @GET("apkLatestVersion")
    Call<ResultVO> apkLatestVersion(@Header("accessToken") String accessToken);

    @GET("getIncrementalUpdates")
    Call<ResultVO> getIncrementalUpdatesUrl(@Header("accessToken") String accessToken, @Query("params") String params);

    @FormUrlEncoded
    @POST("postMessage")
    Call<ResultVO> postMessage(@Header("accessToken") String accessToken, @Field("params") String params);

    @GET("getSimpleGroupTags")
    Call<ResultVO> getSimpleGroupTags(@Header("accessToken") String accessToken);

    class Creator {
        public static ApiService newApiService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.Base_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
