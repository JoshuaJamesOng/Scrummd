package com.ongtonnesoup.scrum.utils;

import android.content.Context;

import com.ongtonnesoup.scrum.observers.ModelChangedObserver;
import com.ongtonnesoup.scrum.presenters.NumberPresenter;
import com.ongtonnesoup.scrum.presenters.PopupPresenter;
import com.ongtonnesoup.scrum.presenters.SettingsPresenter;
import com.ongtonnesoup.scrum.views.MainActivity;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.facades.ArgbEvaluatorFacade;
import com.ongtonnesoup.scrum.android.fragments.NumberFragment;
import com.ongtonnesoup.scrum.views.PopupFragment;
import com.ongtonnesoup.scrum.views.SettingsFragment;
import com.ongtonnesoup.scrum.interfaces.ColourBlender;
import com.ongtonnesoup.scrum.models.ColoursModel;
import com.ongtonnesoup.scrum.decorators.NumberModelDecorator;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
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
        SelectedNumberModel.class,
        MainPresenter.class,
        SettingsPresenter.class,
        PopupPresenter.class,
        NumberPresenter.class,
        ModelChangedObserver.class,
        ColoursModel.class},
        library = true, complete = true)
public class InjectionModule {

    private final Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public SelectedNumberModel provideNumberModelManager(PersistenceProxy persistenceProxy, NumberModelFacade numberModelFacade) {
        return new SelectedNumberModel(persistenceProxy, numberModelFacade);
    }

    @Provides
    @Singleton
    public NumberModelDecorator provideNumberDecorator() {
        return new NumberModelDecorator();
    }

    @Provides
    @Singleton
    public ColourTheme<Integer> provideColourTheme() {
        return new ColourTheme<Integer>();
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public ColoursModel provideColourThemeManager() {
        return new ColoursModel();
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
    @Singleton
    public NumberModelFacade provideNumberModelInteractor() {
        return new NumberModelFacade();
    }
}
