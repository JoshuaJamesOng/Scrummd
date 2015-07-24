package com.ongtonnesoup.scrum.utils;

import android.content.Context;

import com.ongtonnesoup.scrum.views.MainActivity;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.facades.ArgbEvaluatorFacade;
import com.ongtonnesoup.scrum.android.fragments.NumberFragment;
import com.ongtonnesoup.scrum.android.fragments.PopupFragment;
import com.ongtonnesoup.scrum.android.fragments.SettingsFragment;
import com.ongtonnesoup.scrum.interfaces.ColourBlender;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.decorators.NumberModelDecorator;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrum.proxys.PersistenceProxy;
import com.ongtonnesoup.scrum.proxys.ResourceProxy;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;
import com.ongtonnesoup.scrum.presenters.MainPresenter;

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
        ResourceProxy.class,
        NumberModelDecorator.class,
        NumberModelManager.class,
        MainPresenter.class,
        ColourThemeManager.class},
        library = true, complete = true)
public class InjectionModule {

    private final Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public NumberModelManager provideNumberModelManager(PersistenceProxy persistenceProxy, NumberModelFacade numberModelFacade) {
        return new NumberModelManager(persistenceProxy, numberModelFacade);
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
    public ResourceProxy provideResourceManager() {
        return new ResourceProxy(mContext.getResources());
    }

    @Provides
    @Singleton
    public PersistenceProxy providePersistenceManager() {
        return new PersistenceProxy(mContext.getSharedPreferences(PersistenceProxy.SHARED_PREFERENCES, Context.MODE_PRIVATE));
    }

    @Provides
    public ColourBlender provideColourBlender() {
        return new ArgbEvaluatorFacade();
    }

    @Provides
    public NumberModelFacade provideNumberModelInteractor() {
        return new NumberModelFacade();
    }
}
