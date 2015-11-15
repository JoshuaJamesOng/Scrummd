package com.ongtonnesoup.scrummd.domain.interfaces;

import com.ongtonnesoup.scrummd.api.model.ApiResponse;

public interface ApiServiceCallback {

    void onEstimateSubmitSuccess(ApiResponse response);

    void onEstimateSubmitError(int statusCode);

}
