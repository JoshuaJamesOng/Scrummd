package com.ongtonnesoup.scrum.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.views.adapters.NumberAdapter;

import javax.inject.Inject;

public class PopupFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    @Inject
    NumberModel mNumberModel;
    private GridView mGridview;
    private NumberAdapter mAdapter;
    private int mTextColor;

    public static PopupFragment newInstance() {
        return new PopupFragment();
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

        View view = inflater.inflate(R.layout.fragment_settings, null);
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

    public void setTextColor(int c) {
        mTextColor = c;
    }

}
