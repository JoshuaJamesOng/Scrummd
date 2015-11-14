package com.ongtonnesoup.scrummd.domain.facades;

import com.ongtonnesoup.scrummd.api.ScrummdApi;
import com.ongtonnesoup.scrummd.api.model.ApiResponse;
import com.ongtonnesoup.scrummd.domain.interfaces.ApiServiceCallback;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ApiServiceFacade {

    ScrummdApi mScrummdApi;

    public ApiServiceFacade(String baseUrl) {
        mScrummdApi = new ScrummdApi(baseUrl);
    }

    public void submit(String estimate, final ApiServiceCallback callback) {
        mScrummdApi.sendEstimate(estimate, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
                ApiResponse apiResponse = response.body();
                if (apiResponse != null && response.isSuccess()) {
                    callback.onEstimateSubmitSuccess(apiResponse);
                } else {
                    callback.onEstimateSubmitError();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onEstimateSubmitError();
            }
        });
    }
}
