package com.ongtonnesoup.scrum.utils;

import android.content.Context;
import android.content.res.Resources;

import com.ongtonnesoup.scrum.MainActivity;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.views.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.views.adapters.NumberFragmentPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {
        MainActivity.class,
        NumberFragment.class,
        PopupFragment.class,
        NumberAdapter.class,
        NumberFragmentPagerAdapter.class,
        ColourThemeManager.class},
        library = true, complete = true)
public class InjectionModule {

    private Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    public NumberModel provideNumber() {
        return new NumberModel();
    }

    @Provides
    @Singleton
    public ColourTheme provideColourTheme() {
        return new ColourTheme();
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public Resources provideResources() {
        return mContext.getResources();
    }

    @Provides
    @Singleton
    public ColourThemeManager provideColourThemeManager() {
        return new ColourThemeManager();
    }

}
