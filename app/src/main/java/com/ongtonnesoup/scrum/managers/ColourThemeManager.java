package com.ongtonnesoup.scrum.managers;

import android.content.res.Resources;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.ThemeUpdated;
import com.ongtonnesoup.scrum.events.UpdateTheme;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class ColourThemeManager {

    public static final int POOL_SIZE = 9;
    private final int[] mBackgroundColors;
    private final int[] mFillColors;
    private final int[] mStatusBarColors;
    @Inject
    ColourTheme mColourTheme;

    public ColourThemeManager(Resources resources) {
        ScrummdApplication.inject(this);
        mBackgroundColors = resources.getIntArray(R.array.background_colors);
        mFillColors = resources.getIntArray(R.array.fill_colours);
        mStatusBarColors = resources.getIntArray(R.array.status_bar_colors);
        updateTheme(0);
    }

    void updateTheme(int index) {
        mColourTheme.setPrimary(mBackgroundColors[index]);
        mColourTheme.setSecondary(mStatusBarColors[index]);
        mColourTheme.setAccent(mFillColors[index]);
    }

    @Subscribe
    public void themeColourChanged(UpdateTheme event) {
        updateTheme(event.getColourIndex());
        ScrummdApplication.post(ThemeUpdated.Factory.createEvent());
    }
}
