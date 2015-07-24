package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.proxys.PersistenceProxy;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

import java.util.List;

import javax.inject.Inject;

public class NumberModelManager {

    public static final String KEY_MODEL = "KEY_MODEL";
    protected PersistenceProxy mPersitenceManager;
    private NumberModel mCurrentModel;
    private NumberModelFacade mNumberModelFacade;

    @Inject
    public NumberModelManager(PersistenceProxy persistenceProxy, NumberModelFacade numberModelFacade) {
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

    public boolean setCurrentModel(String modelName) {
        boolean modelChanged = false;
        for (NumberModel model : getModels()) {
            if (model.getName().equalsIgnoreCase(modelName)) {
                if (model != mCurrentModel) {
                    modelChanged = true;
                    mPersitenceManager.persist(KEY_MODEL, model.getName());
                }

                mCurrentModel = model;
            }
        }
        return modelChanged;
    }

    public List<NumberModel> getModels() {
        return mNumberModelFacade.getModels();
    }

    public List<String> getModelNames() {
        return mNumberModelFacade.getModelNames();
    }

    private NumberModel load() {
        String modelName = mPersitenceManager.load(KEY_MODEL);
        NumberModel persistedModel = null;
        for (NumberModel model : getModels()) {
            if (model.getName().equalsIgnoreCase(modelName)) {
                persistedModel = model;
                break;
            }
        }
        return persistedModel;
    }
}
