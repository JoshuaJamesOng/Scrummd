package com.ongtonnesoup.scrum.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.events.ModelChanged;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrum.presenters.SettingsPresenter;
import com.ongtonnesoup.scrum.proxys.ResourceProxy;
import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SettingsFragment extends DialogFragment implements SettingsView {

    private static final String KEY_BACKGROUND_COLOR = "KEY_Background_Color";

    @InjectView(R.id.dialog_options_container)
    protected LinearLayout mOptionsContainer;
    @Inject
    protected Context mContext;
    @Inject
    protected SelectedNumberModel mSelectedNumberModel;
    @Inject
    protected ResourceProxy mResourcesProxy;

    private int mThemeColor;
    private RadioGroup mRadioGroup;
    private SettingsPresenter mPresenter;
    private SettingsViewBuilder mBuilder;

    public static SettingsFragment newInstance(int color) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BACKGROUND_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrummdApplication.inject(this);
        mPresenter = new SettingsPresenter(this);
        mBuilder = new SettingsViewBuilder();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.inject(this, view);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().setCanceledOnTouchOutside(true);

        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(KEY_BACKGROUND_COLOR)) {
                mThemeColor = arguments.getInt(KEY_BACKGROUND_COLOR);
            }
        }

        mPresenter.getModelOptions();

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
    public void showModelOptions(List<String> models) {
        mRadioGroup = new RadioGroup(mContext);
        int checked = mBuilder.createModelRadioOptions(models);
        mBuilder.checkCurrentModel(mRadioGroup, checked);
        mOptionsContainer.addView(mRadioGroup);
    }

    private class SettingsViewBuilder {

        private int createModelRadioOptions(List<String> models) {

            int checked = -1;
            for (int i = 0; i < models.size(); i++) {
                RadioButton option = createRadioOption(models.get(i));
                if (models.get(i).equalsIgnoreCase(mSelectedNumberModel.getCurrentModel().getName())) {
                    checked = i;
                }
                mRadioGroup.addView(option);
            }

            return checked;
        }

        private RadioButton createRadioOption(String title) {
            RadioButton button = new RadioButton(mContext);
            button.setText(title.toUpperCase());
            button.setTextColor(mResourcesProxy.getPrimaryTextColor());
            button.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        String name = "" + buttonView.getText();
                        NumberModel model = mPresenter.getModelForName(name);
                        if (mSelectedNumberModel.setCurrentModel(model)) {
                            ScrummdApplication.post(new ModelChanged(model));
                            dismiss();
                        }
                    }
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                button.setButtonTintList(ColorStateList.valueOf(mThemeColor));
            }
            return button;
        }

        private void checkCurrentModel(RadioGroup radioGroup, int checkedIndex) {
            if (checkedIndex > -1) {
                radioGroup.check(radioGroup.getChildAt(checkedIndex).getId());
            }
        }
    }
}
