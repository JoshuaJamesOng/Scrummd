package com.ongtonnesoup.scrum.animations;

import android.support.design.widget.FloatingActionButton;
import android.view.animation.RotateAnimation;

public class PopupButtonAnimation extends RotateAnimation {

    private static final int START_ANGLE = 0;
    private static final int END_ANGLE = 50;
    private static final int DURATION = 350;

    private final FloatingActionButton mButton;

    public PopupButtonAnimation(FloatingActionButton button) {
        super(START_ANGLE, END_ANGLE, button.getWidth() / 2, button.getHeight() / 2);
        setDuration(DURATION);
        mButton = button;
        mButton.setAnimation(this);
    }

    public void revertAnimation() {
        mButton.setRotation(START_ANGLE);
    }

}
