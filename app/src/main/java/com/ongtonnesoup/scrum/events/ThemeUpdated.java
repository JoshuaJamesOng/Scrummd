package com.ongtonnesoup.scrum.events;

import com.squareup.otto.Produce;

public class ThemeUpdated {

    public static class Factory {

        public static ThemeUpdated createEvent() {
            return produceEvent();
        }

        @Produce
        public static ThemeUpdated produceEvent() {
            return new ThemeUpdated();
        }
    }

}
