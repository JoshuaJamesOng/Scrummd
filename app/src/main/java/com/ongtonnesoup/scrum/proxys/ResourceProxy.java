package com.ongtonnesoup.scrum.proxys;

import android.content.Context;
import android.content.res.Resources;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;

import javax.inject.Inject;

public class ResourceProxy {

    private final Resources mResources;
    @Inject
    protected Context mContext;

    public ResourceProxy(Resources resources) {
        ScrummdApplication.inject(this);
        mResources = resources;
    }

    public int getPrimaryTextColor() {
        return mResources.getColor(R.color.black);
    }

    public int getSecondaryTextColor() {
        return mResources.getColor(R.color.grey);
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
