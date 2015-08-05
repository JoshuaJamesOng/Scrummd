package com.ongtonnesoup.scrum.animations;

import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class PopupButtonImageFadeInAnimation extends Animation {

    private static final int DURATION = 250;
    private static final int START = 0;
    private static final int END = 1;

    private final FloatingActionButton mButton;
    private float mAlpha;
    private float mConvertedAlpha;

    public PopupButtonImageFadeInAnimation(FloatingActionButton button) {
        setInterpolator(new LinearInterpolator());
        setDuration(DURATION);

        mButton = button;
        mAlpha = START;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        mAlpha = START + interpolatedTime;
        mConvertedAlpha = 255 * mAlpha;
        mButton.getDrawable().setAlpha((int) mConvertedAlpha);
    }

}
