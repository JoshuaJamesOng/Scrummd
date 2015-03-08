package com.ongtonnesoup.scrum.events;

import com.squareup.otto.Produce;

public class EstimateSelected {

    public static class Factory {

        public static String mNumber;

        public static EstimateSelected createEvent(String number) {
            mNumber = number;
            return produceEvent();
        }

        @Produce
        public static EstimateSelected produceEvent() {
            return new EstimateSelected(mNumber);
        }
    }

    private String mNumber;

    public EstimateSelected(String number) {
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }
}
