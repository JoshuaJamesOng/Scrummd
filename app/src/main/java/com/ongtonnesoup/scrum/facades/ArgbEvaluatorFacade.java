package com.ongtonnesoup.scrum.facades;

import android.animation.ArgbEvaluator;

import com.ongtonnesoup.scrum.interfaces.ColourBlender;

public class ArgbEvaluatorFacade implements ColourBlender {

    private final ArgbEvaluator mEvaluator;

    public ArgbEvaluatorFacade() {
        mEvaluator = new ArgbEvaluator();
    }

    @Override
    public int blend(float positionOffset, int start, int end) {
        return (Integer) mEvaluator.evaluate(positionOffset, start, end);
    }

}
