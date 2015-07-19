package com.ongtonnesoup.scrum.utils;

import android.content.Context;

import com.ongtonnesoup.scrum.MainActivity;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.fragments.SettingsFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.NumberModelDecorator;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrum.managers.ResourceManager;
import com.ongtonnesoup.scrum.models.ColourTheme;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {
        MainActivity.class,
        NumberFragment.class,
        PopupFragment.class,
        SettingsFragment.class,
        NumberAdapter.class,
        NumberFragmentPagerAdapter.class,
        ResourceManager.class,
        NumberModelDecorator.class,
        ColourThemeManager.class},
        library = true, complete = true)
public class InjectionModule {

    private final Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public NumberModelManager provideNumberModelManager() {
        return new NumberModelManager();
    }

    @Provides
    @Singleton
    public NumberModelDecorator provideNumberDecorator() {
        return new NumberModelDecorator();
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
