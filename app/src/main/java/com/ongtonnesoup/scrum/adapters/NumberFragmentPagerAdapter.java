package com.ongtonnesoup.scrum.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.android.fragments.NumberFragment;
import com.ongtonnesoup.scrum.models.ColoursModel;
import com.ongtonnesoup.scrum.decorators.NumberModelDecorator;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.domain.decorators.ColourThemeDecorator;
import com.ongtonnesoup.scrummd.domain.factorys.ColourThemeFactory;
import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

import javax.inject.Inject;

public class NumberFragmentPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
    protected ColoursModel mColoursModel;
    @Inject
    protected SelectedNumberModel mSelectedNumberModel;
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
        int colorIndex = mColoursModel.getColorForIndex(i);
        ColourTheme<Integer> theme = getColourTheme(colorIndex);
        ColourThemeDecorator<Integer> themeDecorator = new ColourThemeDecorator<>(theme);
        NumberFragment fragment;
        if (estimate != null) {
            fragment = NumberFragment.newInstance(estimate, themeDecorator.getCircleColor());
        } else {
            fragment = NumberFragment.newInstance(resourceId, themeDecorator.getCircleColor());
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mSelectedNumberModel.getCurrentModel().getNumbers().length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public int getIndex(String value) {
        for (int i = 0; i < mSelectedNumberModel.getCurrentModel().getNumbers().length; i++) {
            if (mSelectedNumberModel.getCurrentModel().getNumbers()[i].equalsIgnoreCase(value)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Index for '" + value + "' does not exist");
    }

    private ColourTheme<Integer> getColourTheme(int i) {
        int primary = mColoursModel.getBackgroundColors()[i];
        int secondary = mColoursModel.getStatusBarColors()[i];
        int accent = mColoursModel.getFillColors()[i];
        return ColourThemeFactory.<Integer>createColourTheme(primary, secondary, accent);
    }
}
