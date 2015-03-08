package com.ongtonnesoup.scrum.utils;

import com.ongtonnesoup.scrum.MainActivity;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.GestureManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.views.NumberPresenter;
import com.ongtonnesoup.scrum.views.adapters.NumberAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {
        MainActivity.class,
        NumberFragment.class,
        GestureManager.class,
        PopupFragment.class,
        NumberAdapter.class,
        NumberPresenter.class,
        ColourThemeManager.class},
        library = true, complete = true)
public class InjectionModule {

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
    public GestureManager provideGestureManager() {
        return new GestureManager();
    }

}
