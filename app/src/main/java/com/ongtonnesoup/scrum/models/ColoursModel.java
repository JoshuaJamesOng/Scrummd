package com.ongtonnesoup.scrum.models;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.proxys.ResourceProxy;
import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

import javax.inject.Inject;

public class ColoursModel {

    private final int[] mBackgroundColors;
    private final int[] mFillColors;
    private final int[] mStatusBarColors;
    @Inject
    ResourceProxy mResources;

    public ColoursModel() {
        ScrummdApplication.inject(this);
        mBackgroundColors = mResources.getBackgroundColors();
        mFillColors = mResources.getFillColors();
        mStatusBarColors = mResources.getStatusBarColors();
    }

    public int[] getBackgroundColors() {
        return mBackgroundColors;
    }

    public int[] getStatusBarColors() {
        return mStatusBarColors;
    }

    public int[] getFillColors() {
        return mFillColors;
    }

    public ColourTheme<Integer> generateNewColourTheme(int index) {
        ColourTheme<Integer> theme = new ColourTheme<>();
        theme.setPrimary(mBackgroundColors[index]);
        theme.setSecondary(mStatusBarColors[index]);
        theme.setAccent(mFillColors[index]);
        return theme;
    }

    public int getColorForIndex(int i) {
        int index;
        if (i < mBackgroundColors.length) {
            index = i;
        } else {
            do {
                index = i - mBackgroundColors.length;
            }
            while (index >= mBackgroundColors.length);
        }
        return index;
    }
}