package com.ronaldong.messi.data.api;

import com.ronaldong.messi.data.entity.remote.ResultVO;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ronaldong on 2016/1/19.
 */
public class Api {


    public static void login(String loginId, String password, String deviceToken, String deviceType, final RequestCallBack requestCallBack) {
        Call<ResultVO> observable = ApiService.Creator.newApiService().login(loginId, password, deviceToken, deviceType);
        observable.enqueue(new Callback<ResultVO>() {
            @Override
            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ResultVO resultVO = response.body();
                    if (resultVO.status.equals("true") && resultVO.statusCode == 20000) {
                        requestCallBack.onSuccess(resultVO.result);
                    } else {
                        requestCallBack.onFailure(resultVO.msg);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                requestCallBack.onFailure(t.getMessage());
            }
        });
    }

    public static void logout(String accessToken,final RequestCallBack requestCallBack) {
        Call<ResultVO> observable = ApiService.Creator.newApiService().logout(accessToken);
        observable.enqueue(new Callback<ResultVO>() {
            @Override
            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ResultVO resultVO = response.body();
                    if (resultVO.status.equals("true") && resultVO.statusCode == 20000) {
                        requestCallBack.onSuccess(resultVO.result);
                    } else {
                        requestCallBack.onFailure(resultVO.msg);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                requestCallBack.onFailure(t.getMessage());
            }
        });
    }


    public static void getIncrementalUpdates(String accessToken, String params, final RequestCallBack requestCallBack) {
        Call<ResultVO> observable = ApiService.Creator.newApiService().getIncrementalUpdatesUrl(accessToken, params);
        observable.enqueue(new Callback<ResultVO>() {
            @Override
            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ResultVO resultVO = response.body();
                    if (resultVO.status.equals("true") && resultVO.statusCode == 20000) {
                        requestCallBack.onSuccess(resultVO.result);
                    } else {
                        requestCallBack.onFailure(resultVO.msg);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                requestCallBack.onFailure(t.getMessage());
            }
        });
    }

    public static void postMessage(String accessToken, String msg, final RequestCallBack requestCallBack) {
        Call<ResultVO> observable = ApiService.Creator.newApiService().postMessage(accessToken, msg);
        observable.enqueue(new Callback<ResultVO>() {
            @Override
            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ResultVO resultVO = response.body();
                    if (resultVO.status.equals("true") && resultVO.statusCode == 20000) {
                        requestCallBack.onSuccess(resultVO.result);
                    } else {
                        requestCallBack.onFailure(resultVO.msg);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                requestCallBack.onFailure(t.getMessage());
            }
        });

    }


    public static void getSimpleGroupTags(String accessToken,final RequestCallBack requestCallBack) {
        Call<ResultVO> observable = ApiService.Creator.newApiService().getSimpleGroupTags(accessToken);
        observable.enqueue(new Callback<ResultVO>() {
            @Override
            public void onResponse(Response<ResultVO> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    ResultVO resultVO = response.body();
                    if (resultVO.status.equals("true") && resultVO.statusCode == 20000) {
                        requestCallBack.onSuccess(resultVO.result);
                    } else {
                        requestCallBack.onFailure(resultVO.msg);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                requestCallBack.onFailure(t.getMessage());
            }
        });
    }
}
