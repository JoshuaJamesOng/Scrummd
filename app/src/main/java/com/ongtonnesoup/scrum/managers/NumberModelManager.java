package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;
import com.ongtonnesoup.scrum.models.numbers.ScrumNumberModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberModelManager {

    private final List<NumberModel> mModels;

    public NumberModelManager() {
        mModels = Arrays.asList(new ScrumNumberModel(), new FibonacciNumberModel());
    }

    public List<String> getModelNames() {
        List<String> names = new ArrayList<>();
        for (NumberModel model : mModels) {
            names.add(model.getName());
        }
        return names;
    }
}
