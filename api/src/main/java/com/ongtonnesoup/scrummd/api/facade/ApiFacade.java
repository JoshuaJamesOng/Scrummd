package com.ongtonnesoup.scrummd.api.facade;

import com.ongtonnesoup.scrummd.api.model.ApiResponse;

import retrofit.Callback;

public interface ApiFacade {

    void sendEstimate(String estimate, Callback<ApiResponse> callback);

}
