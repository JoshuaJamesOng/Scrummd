package com.ongtonnesoup.scrum.presenters;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrum.views.SettingsView;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

import java.util.List;

import javax.inject.Inject;

public class SettingsPresenter {

    @Inject
    NumberModelFacade mNumberModelFacade;
    @Inject
    SelectedNumberModel mNumberModel;

    private final SettingsView mView;

    public SettingsPresenter(SettingsView view) {
        ScrummdApplication.inject(this);
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
