package com.ongtonnesoup.scrummd.presentation.presenters;

import com.ongtonnesoup.scrummd.presentation.PresentationModule;
import com.ongtonnesoup.scrummd.presentation.events.EstimateSelected;
import com.ongtonnesoup.scrummd.presentation.events.PopupClosed;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.presentation.views.PopupView;

import javax.inject.Inject;

public class PopupPresenter {

    @Inject
    SelectedNumberModel mSelectedNumberModel;

    private final PopupView mView;

    public PopupPresenter(PopupView view) {
        PresentationModule.inject(this);
        mView = view;
    }

    public void onPopupCreated() {
        mView.showModel(mSelectedNumberModel);
    }

    public void onPopupClosed() {
        PresentationModule.post(new PopupClosed());
    }

    public void onEstimateSelected(int position) {
        String selectedEstimate = mSelectedNumberModel.getCurrentModel().getNumbers()[position];
        PresentationModule.post(new EstimateSelected(selectedEstimate));
        PresentationModule.post(new PopupClosed());
        mView.closePopup();
    }

}
