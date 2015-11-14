package com.ongtonnesoup.scrummd.api.service;

import com.ongtonnesoup.scrummd.api.model.ApiResponse;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ScrummdService {

    @FormUrlEncoded
    @POST("/scrummd/estimate")
    Call<ApiResponse> postEstimate(@Field("estimate") String estimate);

}
