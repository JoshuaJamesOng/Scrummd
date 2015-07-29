package com.ongtonnesoup.scrummd.presentation.events;

public class EstimateSelected {

    private final String mNumber;

    public EstimateSelected(String number) {
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }
}
