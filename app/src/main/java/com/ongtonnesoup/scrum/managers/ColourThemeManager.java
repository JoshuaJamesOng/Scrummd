package com.ongtonnesoup.scrum.managers;

import android.content.res.Resources;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.ColourTheme;

import java.util.Random;

import javax.inject.Inject;

public class ColourThemeManager {

    @Inject
    Resources mResources;
    private final int[] mBackgroundColors;
    private final int[] mFillColors;
    private final int[] mStatusBarColors;

    public ColourThemeManager() {
        ScrummdApplication.inject(this);
        mBackgroundColors = mResources.getIntArray(R.array.background_colors);
        mFillColors = mResources.getIntArray(R.array.fill_colours);
        mStatusBarColors = mResources.getIntArray(R.array.status_bar_colors);
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

    public ColourTheme generateNewColourTheme(int index) {
        ColourTheme theme = new ColourTheme();
        theme.setPrimary(mBackgroundColors[index]);
        theme.setSecondary(mStatusBarColors[index]);
        theme.setAccent(mFillColors[index]);
        return theme;
    }

}
