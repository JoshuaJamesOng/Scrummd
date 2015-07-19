package com.ongtonnesoup.scrum.managers;

import android.content.Context;
import android.content.res.Resources;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;

import javax.inject.Inject;

public class ResourceManager {

    @Inject
    protected Context mContext;

    private final Resources mResources;

    public ResourceManager(Resources resources) {
        ScrummdApplication.inject(this);
        mResources = resources;
    }

    public int[] getBackgroundColors() {
        return getColorArray(R.array.background_colors);
    }

    public int[] getFillColors() {
        return getColorArray(R.array.fill_colours);
    }

    public int[] getStatusBarColors() {
        return getColorArray(R.array.status_bar_colors);
    }

    private int[] getColorArray(int id) {
        return mResources.getIntArray(id);
    }

    public int findResourceIdentifier(String resourceName) {
        return mResources.getIdentifier(resourceName, "drawable", mContext.getPackageName());
    }
}