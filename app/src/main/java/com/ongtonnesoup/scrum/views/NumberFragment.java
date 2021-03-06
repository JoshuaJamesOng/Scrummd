package com.ongtonnesoup.scrum.views;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrummd.presentation.presenters.NumberPresenter;
import com.ongtonnesoup.scrummd.presentation.views.NumberView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NumberFragment extends Fragment implements NumberView {

    private static final String KEY_ESTIMATE = "KEY_Estimate";
    private static final String KEY_COLOR_ID = "KEY_Color_Id";
    private static final String KEY_RESOURCE_ID = "KEY_Resource_Id";

    @InjectView(R.id.circle_view)
    protected FloatingActionButton mCircleView;
    @InjectView(R.id.estimate_view)
    protected TextView mEstimateView;
    @InjectView(R.id.image_view)
    protected ImageView mImageView;

    private NumberPresenter mPresenter;

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
        mPresenter = new NumberPresenter(this);
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
                mPresenter.setEstimate(estimate);
            } else if (arguments.containsKey(KEY_RESOURCE_ID)) {
                int resourceId = arguments.getInt(KEY_RESOURCE_ID);
                mPresenter.setIcon(resourceId);
            }
            if (arguments.containsKey(KEY_COLOR_ID)) {
                int colorId = arguments.getInt(KEY_COLOR_ID);
                setColor(colorId);
            }
        }

        mCircleView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mPresenter.submitEstimate();
                return true;
            }
        });

        mPresenter.onNumberCreated();

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

    @Override
    public void showEstimate(String number) {
        mEstimateView.setText(number);
        mImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showIcon(int resourceId) {
        mImageView.setBackgroundResource(resourceId);
        mImageView.setVisibility(View.VISIBLE);
        mEstimateView.setText("");
    }

    @Override
    public void setColor(int color) {
        mCircleView.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
