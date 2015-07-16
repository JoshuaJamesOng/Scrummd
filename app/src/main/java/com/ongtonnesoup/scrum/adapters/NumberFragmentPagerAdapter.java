package com.ongtonnesoup.scrum.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;

import javax.inject.Inject;

public class NumberFragmentPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
    protected ColourThemeManager mColourThemeManager;
    @Inject
    protected NumberModel mNumberModel;

    public NumberFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        ScrummdApplication.inject(this);
    }

    @Override
    public Fragment getItem(int i) {
        String number = mNumberModel.getNumbers()[i];
        ColourTheme theme = mColourThemeManager.generateNewColourTheme(i);
        return NumberFragment.newInstance(number, theme.getCircleColor());
    }

    @Override
    public int getCount() {
        return mNumberModel.getNumbers().length;
    }

    public int getIndex(String value) {
        for (int i = 0; i < mNumberModel.getNumbers().length; i++) {
            if (mNumberModel.getNumbers()[i].equalsIgnoreCase(value)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Index for '" + value + "' does not exist");
    }

}
