package com.ongtonnesoup.scrum.utils;

import android.content.Context;
import android.content.res.Resources;

import com.ongtonnesoup.scrum.MainActivity;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.ResourceManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;
import com.ongtonnesoup.scrum.models.numbers.ScrumNumberModel;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {
        MainActivity.class,
        NumberFragment.class,
        PopupFragment.class,
        NumberAdapter.class,
        NumberFragmentPagerAdapter.class,
        ResourceManager.class,
        ColourThemeManager.class},
        library = true, complete = true)
public class InjectionModule {

    private Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public NumberModel provideNumber() {
        return new ScrumNumberModel();
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
    @Singleton
    public ColourThemeManager provideColourThemeManager() {
        return new ColourThemeManager();
    }

    @Provides
    @Singleton
    public ResourceManager provideResourceManager() {
        return new ResourceManager(mContext.getResources());
    }

}
