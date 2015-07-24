package com.ongtonnesoup.scrum.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.pavlospt.CircleView;
import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrum.proxys.ResourceProxy;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NumberFragment extends Fragment {

    private static final String KEY_ESTIMATE = "KEY_Estimate";
    private static final String KEY_COLOR_ID = "KEY_Color_Id";
    private static final String KEY_RESOURCE_ID = "KEY_Resource_Id";

    @InjectView(R.id.circle_view)
    protected CircleView mCircleView;
    @InjectView(R.id.image_view)
    protected ImageView mImageView;
    @Inject
    protected NumberModelManager mNumberModelManager;
    @Inject
    protected ResourceProxy mResources;

    public static NumberFragment newInstance(String estimate, int colorId) {
        NumberFragment fragment = new NumberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ESTIMATE, estimate);
        bundle.putInt(KEY_COLOR_ID, colorId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static NumberFragment newInstance(int resourceId, int colorId) {
        NumberFragment fragment = new NumberFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RESOURCE_ID, resourceId);
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
            } else if (arguments.containsKey(KEY_RESOURCE_ID)) {
                int resourceId = arguments.getInt(KEY_RESOURCE_ID);
                setIcon(resourceId);
            }
            if (arguments.containsKey(KEY_COLOR_ID)) {
                int colorId = arguments.getInt(KEY_COLOR_ID);
                setColor(colorId);
            }
        } else {
            setEstimate(mNumberModelManager.getCurrentModel().getInitialValue());
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
        mImageView.setVisibility(View.INVISIBLE);
    }

    private void setIcon(int resourceId) {
        mImageView.setBackgroundResource(resourceId);
        mImageView.setVisibility(View.VISIBLE);
        mCircleView.setTitleText("");
    }

    private void setColor(int color) {
        mCircleView.setFillColor(color);
        mCircleView.setStrokeColor(color);
    }

}
