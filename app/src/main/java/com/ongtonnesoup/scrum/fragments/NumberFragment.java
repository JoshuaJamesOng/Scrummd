package com.ongtonnesoup.scrum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NumberFragment extends Fragment {

    private static final String KEY_ESTIMATE = "KEY_Estimate";
    private static final String KEY_COLOR_ID = "KEY_Color_Id";

    @InjectView(R.id.circle_view)
    protected CircleView mCircleView;
    @Inject
    protected NumberModel mNumberModel;

    public static NumberFragment newInstance(String estimate, int colorId) {
        NumberFragment fragment = new NumberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ESTIMATE, estimate);
        bundle.putInt(KEY_COLOR_ID, colorId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrummdApplication.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_number, container, false);
        ButterKnife.inject(this, view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(KEY_ESTIMATE)) {
                String estimate = arguments.getString(KEY_ESTIMATE);
                setEstimate(estimate);
            }
            if (arguments.containsKey(KEY_COLOR_ID)) {
                int colorId = arguments.getInt(KEY_COLOR_ID);
                setColor(colorId);
            }
        } else {
            setEstimate(mNumberModel.getInitialValue());
        }

        return view;
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

    private void setEstimate(String number) {
        mCircleView.setTitleText(number);
    }

    private void setColor(int color) {
        mCircleView.setFillColor(color);
        mCircleView.setStrokeColor(color);
    }

}
