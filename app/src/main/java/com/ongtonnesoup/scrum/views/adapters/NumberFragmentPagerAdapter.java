package com.ongtonnesoup.scrum.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;

import javax.inject.Inject;

public class NumberFragmentPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
    ColourThemeManager mColourThemeManager;
    private String[] values;

    public NumberFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        ScrummdApplication.inject(this);
        values = NumberModel.getValues();
    }

    @Override
    public Fragment getItem(int i) {
        String number = values[i];
        ColourTheme theme = mColourThemeManager.generateNewColourTheme(i);
        return NumberFragment.newInstance(number, theme);
    }

    @Override
    public int getCount() {
        return values.length;
    }

    public int getIndex(String value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equalsIgnoreCase(value)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Index for '" + value + "' does not exist");
    }

}
