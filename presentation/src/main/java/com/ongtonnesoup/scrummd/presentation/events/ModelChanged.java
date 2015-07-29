package com.ongtonnesoup.scrummd.presentation.events;

import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

public class ModelChanged {

    private final NumberModel mModel;

    public ModelChanged(NumberModel model) {
        mModel = model;
    }

    public NumberModel getModel() {
        return mModel;
    }
}
