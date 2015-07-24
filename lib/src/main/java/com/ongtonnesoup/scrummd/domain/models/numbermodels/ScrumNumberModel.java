package com.ongtonnesoup.scrummd.domain.models.numbermodels;

public class ScrumNumberModel extends NumberModel {

    public ScrumNumberModel() {
        mName = "Scrum";
        setNumbers(new String[]{"0", "1/2", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "R.drawable.ic_coffee"});
        setInitialValue(0);
    }
}
