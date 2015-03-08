package com.ongtonnesoup.scrum.events;

import com.ongtonnesoup.scrum.models.TouchType;
import com.squareup.otto.Produce;

public class TouchGesture {

    private final TouchType mTouchType;

    private TouchGesture(TouchType touchType) {
        mTouchType = touchType;
    }

    public TouchType getTouch() {
        return mTouchType;
    }

    public static class Factory {

        public static TouchType mTouchType = TouchType.NONE;

        public static TouchGesture createEvent(TouchType direction) {
            mTouchType = direction;
            return produceTouchEvent();
        }

        @Produce
        public static TouchGesture produceTouchEvent() {
            return new TouchGesture(mTouchType);
        }
    }
}
