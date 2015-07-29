package com.ongtonnesoup.scrum.presenters;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.events.PopupClosed;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrum.views.PopupView;

import javax.inject.Inject;

public class PopupPresenter {

    @Inject
    SelectedNumberModel mSelectedNumberModel;

    private final PopupView mView;

    public PopupPresenter(PopupView view) {
        ScrummdApplication.inject(this);
        mView = view;
    }

    public void onEstimateSelected(int position) {
        String selectedEstimate = mSelectedNumberModel.getCurrentModel().getNumbers()[position];
        ScrummdApplication.post(new EstimateSelected(selectedEstimate));
        ScrummdApplication.post(new PopupClosed());
        mView.closePopup();
    }
}
