package com.ongtonnesoup.scrum.presenters;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.events.ModelChanged;
import com.ongtonnesoup.scrum.events.PopupClosed;
import com.ongtonnesoup.scrum.interfaces.ColourBlender;
import com.ongtonnesoup.scrum.models.ColoursModel;
import com.ongtonnesoup.scrum.views.MainView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class MainPresenter {

    private final MainView mView;
    private final int[] mSecondaryColors;
    private final int[] mAccentColors;

    @Inject
    ColoursModel mColoursModel;
    @Inject
    ColourBlender mColourBlender;


    public MainPresenter(MainView view) {
        ScrummdApplication.inject(this);
        mView = view;
        mSecondaryColors = mColoursModel.getBackgroundColors();
        mAccentColors = mColoursModel.getStatusBarColors();
    }

    public void showSettings() {
        mView.showSettings();
        mView.onSettingsOpened();
    }

    public void showEstimatePopup() {
        mView.showEstimatePicker();
        mView.onEstimatePickerOpened();
    }

    public void onDrag(int position, float positionOffset, int positionOffsetPixels) {
        int targetPosition = position + 1;
        position = mColoursModel.getColorForIndex(position);
        targetPosition = mColoursModel.getColorForIndex(targetPosition);

        int background = calculateColor(positionOffset, mSecondaryColors[position], mSecondaryColors[targetPosition]);
        int status = calculateColor(positionOffset, mAccentColors[position], mAccentColors[targetPosition]);
        int popup = calculateColor(positionOffset, mSecondaryColors[position], mSecondaryColors[targetPosition]);
        int button = calculateColor(positionOffset, mAccentColors[position], mAccentColors[targetPosition]);

        mView.setTheming(background, status, popup, button);
    }

    @Subscribe
    public void onEstimateChanged(EstimateSelected event) {
        if (event != null) {
            mView.changeEstimate(event.getNumber());
        }
    }

    @Subscribe
    public void onModelChanged(ModelChanged event) {
        if (event != null) {
            mView.onNumberModelChanged();
        }
    }

    @Subscribe
    public void onPopupClosed(PopupClosed event) {
        if (event != null) {
            mView.onEstimatePickerClosed();
        }
    }

    private int calculateColor(float positionOffset, int start, int end) {
        return mColourBlender.blend(positionOffset, start, end);
    }


}
