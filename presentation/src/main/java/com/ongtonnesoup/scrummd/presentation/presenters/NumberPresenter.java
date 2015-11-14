package com.ongtonnesoup.scrummd.presentation.presenters;

import com.ongtonnesoup.scrummd.presentation.PresentationModule;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.presentation.views.NumberView;

import javax.inject.Inject;

public class NumberPresenter {

    @Inject
    SelectedNumberModel mSelectedNumberModel;

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
        System.out.println("JJO: Submit estimate" + mEstimate);
    }
}
