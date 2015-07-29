package com.ongtonnesoup.scrummd.presentation.presenters;

import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;
import com.ongtonnesoup.scrummd.presentation.PresentationModule;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;
import com.ongtonnesoup.scrummd.presentation.views.SettingsView;

import java.util.List;

import javax.inject.Inject;

public class SettingsPresenter {

    @Inject
    NumberModelFacade mNumberModelFacade;
    @Inject
    SelectedNumberModel mNumberModel;

    private final SettingsView mView;

    public SettingsPresenter(SettingsView view) {
        PresentationModule.inject(this);
        mView = view;
    }

    public void getModelOptions() {
        List<String> options = mNumberModelFacade.getModelNames();
        mView.showModelOptions(options);
    }

    public List<NumberModel> getModels() {
        return mNumberModelFacade.getModels();
    }

    public List<String> getModelNames() {
        return mNumberModelFacade.getModelNames();
    }

    public NumberModel getModelForName(String name) {
        NumberModel model = null;
        for (NumberModel numberModel : getModels()) {
            if (numberModel.getName().equalsIgnoreCase(name)) {
                model = numberModel;
                break;
            }
        }
        return model;
    }
}
