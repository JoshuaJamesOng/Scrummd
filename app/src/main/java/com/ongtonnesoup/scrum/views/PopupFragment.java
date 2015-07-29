package com.ongtonnesoup.scrum.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.adapters.NumberAdapter;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrum.presenters.PopupPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PopupFragment extends DialogFragment implements PopupView, AdapterView.OnItemClickListener {

    @InjectView(R.id.number_selection_gridview) GridView mGridView;

    private static final String KEY_Y_POS = "KEY_Y_Pos";
    private static final String KEY_TEXT_COLOR = "KEY_Text_Color";

    protected PopupPresenter mPresenter;
    protected int mTextColor;

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
        mPresenter = new PopupPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().setCanceledOnTouchOutside(true);

        View view = inflater.inflate(R.layout.fragment_popup, container);
        ButterKnife.inject(this, view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(KEY_Y_POS)) {
                setPosition(arguments.getInt(KEY_Y_POS));
            }
            if (arguments.containsKey(KEY_TEXT_COLOR)) {
                mTextColor = arguments.getInt(KEY_TEXT_COLOR);
            }
        }

        mPresenter.onPopupCreated();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ScrummdApplication.register(mPresenter);
    }

    @Override
    public void onStart() {
        super.onStart();
        removeBackgroundDim();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mPresenter.onPopupClosed();
        super.onDismiss(dialog);
    }

    @Override
    public void onPause() {
        ScrummdApplication.unregister(mPresenter);
        super.onPause();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.onEstimateSelected(position);
    }

    @Override
    public void showModel(SelectedNumberModel model) {
        NumberAdapter adapter = new NumberAdapter(model, mTextColor);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);
        onPopupOpen();
    }

    @Override
    public void closePopup() {
        dismiss();
    }

    private void onPopupOpen() {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_slide_in_bottom);
        mGridView.setAnimation(anim);
        anim.start();
    }

    private void setPosition(int y) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        layoutParams.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.y = y;
        window.setAttributes(layoutParams);
    }

    private void removeBackgroundDim() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }
}
