package com.ongtonnesoup.scrummd.domain.events;

public class EstimateSelected {

    private final String mNumber;

    public EstimateSelected(String number) {
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }
}
