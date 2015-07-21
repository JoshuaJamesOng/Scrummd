package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;
import com.ongtonnesoup.scrum.models.numbers.ScrumNumberModel;
import com.ongtonnesoup.scrum.models.numbers.ShirtNumberModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class NumberModelManager {

    public static final String KEY_MODEL = "KEY_MODEL";
    protected PersistenceManager mPersitenceManager;
    private final List<NumberModel> mModels;
    private NumberModel mCurrentModel;

    @Inject
    public NumberModelManager(PersistenceManager persistenceManager) {
        mPersitenceManager = persistenceManager;
        mModels = Arrays.asList(new ScrumNumberModel(), new FibonacciNumberModel(), new ShirtNumberModel());
        mCurrentModel = load();
        if (mCurrentModel == null) {
            mCurrentModel = mModels.get(0);
        }
    }

    public NumberModel getCurrentModel() {
        return mCurrentModel;
    }

    public boolean setCurrentModel(String modelName) {
        boolean modelChanged = false;
        for (NumberModel model : mModels) {
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

    public List<String> getModelNames() {
        List<String> names = new ArrayList<>();
        for (NumberModel model : mModels) {
            names.add(model.getName());
        }
        return names;
    }

    private NumberModel load() {
        String modelName = mPersitenceManager.load(KEY_MODEL);
        NumberModel persistedModel = null;
        for (NumberModel model : mModels) {
            if (model.getName().equalsIgnoreCase(modelName)) {
                persistedModel = model;
                break;
            }
        }
        return persistedModel;
    }
}
