package com.ongtonnesoup.scrum.events;

import com.squareup.otto.Produce;

public class EstimateSelected {

    private final String mNumber;

    private EstimateSelected(String number) {
        mNumber = number;
    }

    public String getNumber() {
        return mNumber;
    }

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
}
