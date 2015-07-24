package com.ongtonnesoup.scrum.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.android.fragments.NumberFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.NumberModelDecorator;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

import javax.inject.Inject;

public class NumberFragmentPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
    protected ColourThemeManager mColourThemeManager;
    @Inject
    protected NumberModelManager mNumberModelManager;
    @Inject
    protected NumberModelDecorator mNumberModelDecorator;

    public NumberFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        ScrummdApplication.inject(this);
    }

    @Override
    public Fragment getItem(int i) {
        String estimate = mNumberModelDecorator.getNumber(i);
        int resourceId = mNumberModelDecorator.getResourceIdentifier(i);
        int colorIndex = mColourThemeManager.getColorForIndex(i);
        ColourTheme theme = mColourThemeManager.generateNewColourTheme(colorIndex);

        NumberFragment fragment;
        if (estimate != null) {
            fragment = NumberFragment.newInstance(estimate, theme.getCircleColor());
        } else {
            fragment = NumberFragment.newInstance(resourceId, theme.getCircleColor());
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumberModelManager.getCurrentModel().getNumbers().length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public int getIndex(String value) {
        for (int i = 0; i < mNumberModelManager.getCurrentModel().getNumbers().length; i++) {
            if (mNumberModelManager.getCurrentModel().getNumbers()[i].equalsIgnoreCase(value)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Index for '" + value + "' does not exist");
    }

}
