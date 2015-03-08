package com.ongtonnesoup.scrum.managers;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.TouchGesture;
import com.ongtonnesoup.scrum.models.TouchType;

public class GestureManager implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final float SWIPE_MIN_DISTANCE = 100;

    public GestureManager() {
        ScrummdApplication.inject(this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        ScrummdApplication.post(TouchGesture.Factory.createEvent(TouchType.TAP));

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
            ScrummdApplication.post(TouchGesture.Factory.createEvent(TouchType.SWIPE_LEFT));
        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
            ScrummdApplication.post(TouchGesture.Factory.createEvent(TouchType.SWIPE_RIGHT));
        }

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
