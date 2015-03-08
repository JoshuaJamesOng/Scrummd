package com.ongtonnesoup.scrum.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.events.TouchGesture;
import com.ongtonnesoup.scrum.models.TouchType;
import com.ongtonnesoup.scrum.views.NumberPresenter;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class NumberFragment extends Fragment {

    public static final String TAG = "TAG_NumberFragment";

    @Inject
    public NumberModel mNumberModel;
    private NumberPresenter mNumberPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrummdApplication.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ScrummdApplication.register(this);
        ScrummdApplication.register(mNumberPresenter);
    }

    @Override
    public void onPause() {
        ScrummdApplication.unregister(mNumberPresenter);
        ScrummdApplication.unregister(this);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_number, container, false);

        mNumberPresenter = new NumberPresenter(view, this);

        return view;
    }

    @Subscribe
    public void handleTouchEvent(TouchGesture event) {

        if (event.getTouch().equals(TouchType.SWIPE_LEFT)) {
            mNumberModel.nextNumber();
            mNumberPresenter.updateNumber(mNumberModel.getCurrent());
            mNumberPresenter.changeTheme();
        } else if (event.getTouch().equals(TouchType.SWIPE_RIGHT)) {
            mNumberModel.previousNumber();
            mNumberPresenter.updateNumber(mNumberModel.getCurrent());
            mNumberPresenter.changeTheme();
        } else if (event.getTouch().equals(TouchType.TAP)) {
            mNumberPresenter.changeTheme();
        }
    }

    @Subscribe
    public void updateEstimate(EstimateSelected event) {
        mNumberModel.setCurrent(event.getNumber());
        mNumberPresenter.updateNumber(mNumberModel.getCurrent());
    }

}
