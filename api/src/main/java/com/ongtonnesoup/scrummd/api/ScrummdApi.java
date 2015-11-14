package com.ongtonnesoup.scrummd.api;

import com.ongtonnesoup.scrummd.api.facade.ApiFacade;
import com.ongtonnesoup.scrummd.api.model.ApiResponse;
import com.ongtonnesoup.scrummd.api.service.ScrummdService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ScrummdApi implements ApiFacade {

    private final String BASE_URL;
    private final ScrummdService mService;

    public ScrummdApi(String baseUrl) {
        BASE_URL = baseUrl;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(ScrummdService.class);
    }

    public void sendEstimate(String estimate, Callback<ApiResponse> callback) {
        Call<ApiResponse> call = mService.postEstimate(estimate);
        call.enqueue(callback);
    }

}
