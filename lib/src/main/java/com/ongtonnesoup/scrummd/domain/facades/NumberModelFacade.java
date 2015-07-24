package com.ongtonnesoup.scrummd.domain.facades;

import com.ongtonnesoup.scrummd.domain.interfaces.NumberModelInterface;
import com.ongtonnesoup.scrummd.domain.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;
import com.ongtonnesoup.scrummd.domain.models.numbers.ScrumNumberModel;
import com.ongtonnesoup.scrummd.domain.models.numbers.ShirtNumberModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberModelFacade implements NumberModelInterface {

    private List<NumberModel> mModels;

    public NumberModelFacade() {
        mModels = Arrays.asList(new ScrumNumberModel(), new FibonacciNumberModel(), new ShirtNumberModel());
    }

    @Override
    public NumberModel getDefaultModel() {
        return mModels.get(0);
    }

    @Override
    public List<NumberModel> getModels() {
        return mModels;
    }

    @Override
    public List<String> getModelNames() {
        List<String> names = new ArrayList<>();
        for (NumberModel model : mModels) {
            names.add(model.getName());
        }
        return names;
    }

}
