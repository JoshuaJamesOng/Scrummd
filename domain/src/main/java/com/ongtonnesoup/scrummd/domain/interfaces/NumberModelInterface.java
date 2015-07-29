package com.ongtonnesoup.scrummd.domain.interfaces;

import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

import java.util.List;

public interface NumberModelInterface {

    NumberModel getDefaultModel();
    List<NumberModel> getModels();
    List<String> getModelNames();

}
