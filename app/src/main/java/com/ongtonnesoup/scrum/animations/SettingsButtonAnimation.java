package com.ongtonnesoup.scrum.animations;

import android.view.View;
import android.view.animation.RotateAnimation;

public class SettingsButtonAnimation extends RotateAnimation {

    private static final int START_ANGLE = 0;
    private static final int END_ANGLE = 50;
    private static final int DURATION = 350;

    private final View mButton;

    public SettingsButtonAnimation(View button) {
        super(START_ANGLE, END_ANGLE, button.getWidth() / 2, button.getHeight() / 2);
        setDuration(DURATION);
        mButton = button;
        mButton.setAnimation(this);
    }

    public void revertAnimation() {
        mButton.setRotation(START_ANGLE);
    }

}
