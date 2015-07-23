package com.ongtonnesoup.scrum.presenters;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.interfaces.ColourBlender;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.views.MainView;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class MainPresenter {

    private final MainView mView;
    private final int[] mSecondaryColors;
    private final int[] mAccentColors;

    @Inject
    ColourThemeManager mColourThemeManager;
    @Inject
    ColourBlender mColourBlender;


    public MainPresenter(MainView view) {
        ScrummdApplication.inject(this);
        mView = view;
        mSecondaryColors = mColourThemeManager.getBackgroundColors();
        mAccentColors = mColourThemeManager.getStatusBarColors();
    }

    public void showSettings() {
        mView.showSettings();
    }

    public void showEstimatePopup() {
        mView.showEstimatePicker();
    }

    public void onDrag(int position, float positionOffset, int positionOffsetPixels) {
        int targetPosition = position + 1;
        position = mColourThemeManager.getColorForIndex(position);
        targetPosition = mColourThemeManager.getColorForIndex(targetPosition);

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

    private int calculateColor(float positionOffset, int start, int end) {
        return mColourBlender.blend(positionOffset, start, end);
    }


}