package com.ongtonnesoup.scrum.events;


import com.squareup.otto.Produce;

public class UpdateTheme {

    private final Integer mNumber;

    public UpdateTheme(Integer number) {
        mNumber = number;
    }

    public Integer getColourIndex() {
        return mNumber;
    }

    public static class Factory {

        public static Integer mNumber;

        public static UpdateTheme createEvent(Integer number) {
            mNumber = number;
            return produceEvent();
        }

        @Produce
        public static UpdateTheme produceEvent() {
            return new UpdateTheme(mNumber);
        }
    }
}
