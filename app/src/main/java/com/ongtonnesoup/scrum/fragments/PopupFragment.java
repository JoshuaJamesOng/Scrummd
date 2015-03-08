package com.ongtonnesoup.scrum.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.events.ThemeUpdated;
import com.ongtonnesoup.scrum.models.NumberModel;
import com.ongtonnesoup.scrum.views.adapters.NumberAdapter;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class PopupFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "TAG_PopupFragment";

    @Inject
    NumberModel mNumberModel;
    private GridView mGridview;

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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mGridview = (GridView) view.findViewById(R.id.number_selection_gridview);
        mGridview.setAdapter(new NumberAdapter(getActivity(), mNumberModel));
        mGridview.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ScrummdApplication.post(EstimateSelected.Factory.createEvent(mNumberModel.getValues()[position]));
    }

    @Subscribe
    public void updatePopupTextColor(ThemeUpdated event) {
        mGridview.invalidateViews();
    }
}
