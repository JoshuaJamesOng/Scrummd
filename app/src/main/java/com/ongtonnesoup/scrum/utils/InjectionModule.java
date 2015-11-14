package com.ongtonnesoup.scrum.utils;

import android.content.Context;

import com.ongtonnesoup.scrum.BuildConfig;
import com.ongtonnesoup.scrum.Installation;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.decorators.NumberModelDecorator;
import com.ongtonnesoup.scrum.facades.ArgbEvaluatorFacade;
import com.ongtonnesoup.scrum.observers.ModelChangedObserver;
import com.ongtonnesoup.scrum.proxys.AndroidPersistenceProxy;
import com.ongtonnesoup.scrum.proxys.AndroidResourceProxy;
import com.ongtonnesoup.scrum.views.MainActivity;
import com.ongtonnesoup.scrum.views.NumberFragment;
import com.ongtonnesoup.scrum.views.PopupFragment;
import com.ongtonnesoup.scrum.views.SettingsFragment;
import com.ongtonnesoup.scrummd.domain.facades.ApiServiceFacade;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.User;
import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;
import com.ongtonnesoup.scrummd.presentation.interfaces.ColourBlender;
import com.ongtonnesoup.scrummd.presentation.interfaces.PersistenceProxy;
import com.ongtonnesoup.scrummd.presentation.models.ColoursModel;
import com.ongtonnesoup.scrummd.presentation.models.ResourceProxy;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.presentation.presenters.MainPresenter;
import com.ongtonnesoup.scrummd.presentation.presenters.NumberPresenter;
import com.ongtonnesoup.scrummd.presentation.presenters.PopupPresenter;
import com.ongtonnesoup.scrummd.presentation.presenters.SettingsPresenter;

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
        AndroidResourceProxy.class,
        NumberModelDecorator.class,
        SelectedNumberModel.class,
        MainPresenter.class,
        SettingsPresenter.class,
        PopupPresenter.class,
        NumberPresenter.class,
        ModelChangedObserver.class,
        ColoursModel.class,
        ApiServiceFacade.class},
        library = true, complete = true)
public class InjectionModule {

    private final Context mContext;

    public InjectionModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public SelectedNumberModel provideNumberModelManager(PersistenceProxy androidPersistenceProxy, NumberModelFacade numberModelFacade) {
        return new SelectedNumberModel(androidPersistenceProxy, numberModelFacade);
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
        return new AndroidResourceProxy(mContext.getResources());
    }

    @Provides
    @Singleton
    public PersistenceProxy providePersistenceProxy() {
        return new AndroidPersistenceProxy(mContext.getSharedPreferences(AndroidPersistenceProxy.SHARED_PREFERENCES, Context.MODE_PRIVATE));
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

    @Provides
    @Singleton
    public ApiServiceFacade provideApiServiceInteractor() {
        return new ApiServiceFacade(BuildConfig.BASE_URL);
    }

    @Provides
    public User provideUser() {
        return new User(Installation.id(mContext));
    }
}
