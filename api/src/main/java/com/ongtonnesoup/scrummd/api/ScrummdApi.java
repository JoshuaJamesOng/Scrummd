package com.ongtonnesoup.scrummd.api;

import com.ongtonnesoup.scrummd.api.facade.ApiFacade;
import com.ongtonnesoup.scrummd.api.model.ApiResponse;
import com.ongtonnesoup.scrummd.api.service.ScrummdService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrummdApi implements ApiFacade {

    private final ScrummdService mService;

    public ScrummdApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("url")
                .build();

        mService = retrofit.create(ScrummdService.class);
    }

    public void sendEstimate(String estimate) {
        Call<ApiResponse> call = mService.postEstimate(estimate);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Response<ApiResponse> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
