package com.ongtonnesoup.scrummd.presentation.models;

import com.ongtonnesoup.scrummd.presentation.PresentationModule;

import javax.inject.Inject;

public class ColoursModel {

    private final int[] mBackgroundColors;
    private final int[] mFillColors;
    private final int[] mStatusBarColors;
    @Inject
    ResourceProxy mResources;

    public ColoursModel() {
        PresentationModule.inject(this);
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
