package com.ongtonnesoup.scrum.views;

import android.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.events.ThemeUpdated;
import com.ongtonnesoup.scrum.events.UpdateTheme;
import com.squareup.otto.Subscribe;

import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NumberPresenter {

    @Inject
    public ColourTheme mColourTheme;
    private final Random mRandom;
    private final Window mWindow;
    @InjectView(R.id.circle_view)
    public CircleView mCircleView;
    @InjectView(R.id.number_fragment)
    public View mBackgroundView;
    private int mPrevIndex;

    public NumberPresenter(View view, Fragment fragment) {
        ScrummdApplication.inject(this);
        ButterKnife.inject(this, view);

        mWindow = fragment.getActivity().getWindow();
        mRandom = new Random();
        mPrevIndex = -1;

        updateNumber(NumberModel.INITIAL);
        updateColors(null);
    }

    public void updateNumber(String number) {
        mCircleView.setTitleText(number);
    }

    public void changeTheme() {
        int index;
        do {
            index = mRandom.nextInt(ColourThemeManager.POOL_SIZE);
        }
        while (index == mPrevIndex);
        mPrevIndex = index;
        ScrummdApplication.post(UpdateTheme.Factory.createEvent(index));
    }

    private void updateBackground(int color) {
        mBackgroundView.setBackgroundColor(color);
    }

    private void updateFill(int color) {
        mCircleView.setFillColor(color);
        mCircleView.setStrokeColor(color);
    }

    private void updateStatusBar(int color) {
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mWindow.setStatusBarColor(color);
    }

    @Subscribe
    public void updateColors(ThemeUpdated event) {
        updateBackground(mColourTheme.getBackgroundColor());
        updateFill(mColourTheme.getCircleColor());
        updateStatusBar(mColourTheme.getStatusBarColor());
    }

}
