package com.ongtonnesoup.scrum.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.ColourTheme;
import com.ongtonnesoup.scrum.models.NumberModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NumberFragment extends Fragment {

    private static final String KEY_ESTIMATE = "KEY_Estimate";
    private static final String KEY_THEME = "KEY_Theme";

    @InjectView(R.id.circle_view)
    CircleView mCircleView;


    private ColourTheme mColourTheme;

    public static NumberFragment newInstance(String estimate, ColourTheme colourTheme) {
        NumberFragment fragment = new NumberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ESTIMATE, estimate);
        bundle.putParcelable(KEY_THEME, colourTheme);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ColourTheme getColourTheme() {
        return mColourTheme;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrummdApplication.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ScrummdApplication.register(this);
    }

    @Override
    public void onPause() {
        ScrummdApplication.unregister(this);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_number, container, false);
        ButterKnife.inject(this, view);

        if (getArguments() != null) {
            if (getArguments().containsKey(KEY_ESTIMATE)) {
                String estimate = getArguments().getString(KEY_ESTIMATE);
                updateNumber(estimate);
            }
            if (getArguments().containsKey(KEY_THEME)) {
                mColourTheme = getArguments().getParcelable(KEY_THEME);
                updateFill(mColourTheme.getCircleColor());
            }
        } else {
            updateNumber(NumberModel.INITIAL);
        }

        return view;
    }

    public void updateNumber(String number) {
        mCircleView.setTitleText(number);
    }

    private void updateFill(int color) {
        mCircleView.setFillColor(color);
        mCircleView.setStrokeColor(color);
    }

}
