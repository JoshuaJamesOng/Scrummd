package com.ongtonnesoup.scrummd.presentation.interfaces;

import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

public interface PersistenceProxy {

    boolean persist(String key, String value);
    boolean persist(String key, NumberModel model);
    String loadModel();

}
