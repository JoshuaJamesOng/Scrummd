package com.ongtonnesoup.scrum.utils;

import android.content.Context;

import com.ongtonnesoup.scrum.android.activity.MainActivity;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.facades.ArgbEvaluatorFacade;
import com.ongtonnesoup.scrum.android.fragments.NumberFragment;
import com.ongtonnesoup.scrum.android.fragments.PopupFragment;
import com.ongtonnesoup.scrum.android.fragments.SettingsFragment;
import com.ongtonnesoup.scrummd.domain.interfaces.ColourBlender;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.NumberModelDecorator;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrum.managers.PersistenceManager;
import com.ongtonnesoup.scrum.managers.ResourceManager;
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
        ResourceManager.class,
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
    public NumberModelManager provideNumberModelManager(PersistenceManager persistenceManager, NumberModelFacade numberModelFacade) {
        return new NumberModelManager(persistenceManager, numberModelFacade);
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

    @Provides
    @Singleton
    public PersistenceManager providePersistenceManager() {
        return new PersistenceManager(mContext.getSharedPreferences(PersistenceManager.SHARED_PREFERENCES, Context.MODE_PRIVATE));
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
