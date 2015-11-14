package com.ongtonnesoup.scrummd.domain.interfaces;

import com.ongtonnesoup.scrummd.api.model.ApiResponse;

import retrofit.Response;

public interface ApiServiceCallback {

    void onEstimateSubmitSuccess(ApiResponse response);

    void onEstimateSubmitError();

}
