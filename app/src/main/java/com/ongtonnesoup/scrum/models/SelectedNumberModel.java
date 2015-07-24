package com.ongtonnesoup.scrum.models;

import com.ongtonnesoup.scrum.proxys.PersistenceProxy;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

import javax.inject.Inject;

public class SelectedNumberModel {

    protected PersistenceProxy mPersitenceManager;
    private NumberModel mCurrentModel;
    private NumberModelFacade mNumberModelFacade;

    @Inject
    public SelectedNumberModel(PersistenceProxy persistenceProxy, NumberModelFacade numberModelFacade) {
        mPersitenceManager = persistenceProxy;
        mNumberModelFacade = numberModelFacade;
        mCurrentModel = load();
        if (mCurrentModel == null) {
            mCurrentModel = mNumberModelFacade.getDefaultModel();
        }
    }

    public NumberModel getCurrentModel() {
        return mCurrentModel;
    }

    public boolean setCurrentModel(NumberModel model) {
        boolean changed = false;
        if (model != mCurrentModel) {
            changed = true;
            mCurrentModel = model;
        }
        return changed;
    }

    private NumberModel load() {
        String modelName = mPersitenceManager.load(PersistenceProxy.KEY_MODEL);
        NumberModel persistedModel = null;
        for (NumberModel model : mNumberModelFacade.getModels()) {
            if (model.getName().equalsIgnoreCase(modelName)) {
                persistedModel = model;
                break;
            }
        }
        return persistedModel;
    }
}
