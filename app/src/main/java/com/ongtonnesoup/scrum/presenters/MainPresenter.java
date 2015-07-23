package com.ongtonnesoup.scrum.presenters;

import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.views.MainView;
import com.squareup.otto.Subscribe;

public class MainPresenter {

    MainView mView;

    public MainPresenter(MainView view) {
        mView = view;
    }

    public void showSettings() {
        mView.showSettings();
    }

    public void showEstimatePopup() {
        mView.showEstimatePicker();
    }

    public void onDrag(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Subscribe
    public void onEstimateChanged(EstimateSelected event) {
        if (event != null) {
            mView.changeEstimate(event.getNumber());
        }
    }


}
