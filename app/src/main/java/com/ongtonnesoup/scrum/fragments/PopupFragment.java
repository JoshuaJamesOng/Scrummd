package com.ongtonnesoup.scrum.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.views.adapters.NumberAdapter;

import javax.inject.Inject;

public class PopupFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private static String KEY_Y_POS = "KEY_Y_Pos";
    private static String KEY_TEXT_COLOR = "KEY_Text_Color";
    @Inject
    NumberModel mNumberModel;
    private GridView mGridview;
    private NumberAdapter mAdapter;
    private int mTextColor;

    public static PopupFragment newInstance(int y, int color) {
        PopupFragment fragment = new PopupFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_Y_POS, y);
        bundle.putInt(KEY_TEXT_COLOR, color);
        fragment.setArguments(bundle);
        return fragment;
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
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        View view = inflater.inflate(R.layout.fragment_settings, null);

        if (getArguments() != null) {
            if (getArguments().containsKey(KEY_Y_POS)) {
                setPosition(getArguments().getInt(KEY_Y_POS));
            }
            if (getArguments().containsKey(KEY_TEXT_COLOR)) {
                mTextColor = getArguments().getInt(KEY_TEXT_COLOR);
            }
        }

        mGridview = (GridView) view.findViewById(R.id.number_selection_gridview);
        mAdapter = new NumberAdapter(mNumberModel);
        mAdapter.setTextColor(mTextColor);
        mGridview.setAdapter(mAdapter);
        mGridview.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ScrummdApplication.post(EstimateSelected.Factory.createEvent(mNumberModel.getValues()[position]));
        dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

    private void setPosition(int y) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wlp.y = y;
        window.setAttributes(wlp);
    }
}
