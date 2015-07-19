package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;
import com.ongtonnesoup.scrum.models.numbers.ScrumNumberModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberModelManager {

    private final List<NumberModel> mModels;
    private NumberModel mCurrentModel;

    public NumberModelManager() {
        mCurrentModel = new ScrumNumberModel();
        mModels = Arrays.asList(mCurrentModel, new FibonacciNumberModel());
    }

    public NumberModel getCurrentModel() {
        return mCurrentModel;
    }

    public void setCurrentModel(NumberModel model) {
        mCurrentModel = model;
    }

    public List<String> getModelNames() {
        List<String> names = new ArrayList<>();
        for (NumberModel model : mModels) {
            names.add(model.getName());
        }
        return names;
    }
}
