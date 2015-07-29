package com.ongtonnesoup.scrum.proxys;

import android.content.Context;
import android.content.res.Resources;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrummd.presentation.models.ResourceProxy;

import javax.inject.Inject;

public class AndroidResourceProxy implements ResourceProxy {

    private final Resources mResources;
    @Inject
    protected Context mContext;

    public AndroidResourceProxy(Resources resources) {
        ScrummdApplication.inject(this);
        mResources = resources;
    }

    @Override
    public int getPrimaryTextColor() {
        return mResources.getColor(R.color.black);
    }

    @Override
    public int getSecondaryTextColor() {
        return mResources.getColor(R.color.grey);
    }

    @Override
    public int[] getBackgroundColors() {
        return getColorArray(R.array.background_colors);
    }

    @Override
    public int[] getFillColors() {
        return getColorArray(R.array.fill_colours);
    }

    @Override
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
