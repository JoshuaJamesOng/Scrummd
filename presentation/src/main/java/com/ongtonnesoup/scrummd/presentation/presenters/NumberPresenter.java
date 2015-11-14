package com.ongtonnesoup.scrummd.presentation.presenters;

import com.ongtonnesoup.scrummd.api.model.ApiResponse;
import com.ongtonnesoup.scrummd.domain.facades.ApiServiceFacade;
import com.ongtonnesoup.scrummd.domain.interfaces.ApiServiceCallback;
import com.ongtonnesoup.scrummd.presentation.PresentationModule;
import com.ongtonnesoup.scrummd.presentation.models.ResourceProxy;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.presentation.views.NumberView;

import javax.inject.Inject;

import retrofit.Response;

public class NumberPresenter implements ApiServiceCallback {

    @Inject
    SelectedNumberModel mSelectedNumberModel;
    @Inject
    ResourceProxy mResourceProxy;
    @Inject
    ApiServiceFacade mApiService;

    String mUser;

    private final NumberView mView;
    private String mEstimate;
    private int mIcon;

    public NumberPresenter(NumberView view) {
        PresentationModule.inject(this);
        mView = view;
    }

    public void onNumberCreated() {

        if(mIcon > 0) {
            mView.showIcon(mIcon);
        } else {

            if (mEstimate.isEmpty()) {
                getInitialEstimate();
            }
            mView.showEstimate(mEstimate);
        }
    }

    public void setEstimate(String estimate) {
        mEstimate = estimate;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    private void getInitialEstimate() {
        String initialValue = mSelectedNumberModel.getCurrentModel().getInitialValue();
        mEstimate = initialValue;
    }

    public void submitEstimate() {
        mApiService.submit(mUser, mEstimate, this);
    }

    @Override
    public void onEstimateSubmitSuccess(ApiResponse response) {
        String message = mResourceProxy.getSubmitSuccessMessage();
        mView.showMessage(message);
    }

    @Override
    public void onEstimateSubmitError() {
        String message = mResourceProxy.getSubmitErrorMessage();
        mView.showMessage(message);
    }
}
