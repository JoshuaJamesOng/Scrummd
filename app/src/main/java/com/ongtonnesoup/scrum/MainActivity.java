package com.ongtonnesoup.scrum;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.animations.PopupButtonAnimation;
import com.ongtonnesoup.scrum.animations.SettingsButtonAnimation;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.fragments.SettingsFragment;
import com.ongtonnesoup.scrum.presenters.MainPresenter;
import com.ongtonnesoup.scrum.views.MainView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, MainView {

    @InjectView(R.id.add_button)
    FloatingActionButton mAddButton;
    @InjectView(R.id.settings)
    ImageView mSettingsView;

    private FragmentManager mFragmentManager;
    private ViewPager mPager;
    private NumberFragmentPagerAdapter mPagerAdapter;
    private Window mWindow;
    private int mPopupFragmentTextColor;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrummdApplication.inject(this);
        ButterKnife.inject(this);

        mPresenter = new MainPresenter(this);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showEstimatePopup();
            }
        });
        mSettingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showSettings();
            }
        });

        mWindow = getWindow();

        mFragmentManager = getSupportFragmentManager();

        mPager = (ViewPager) findViewById(R.id.fragment_container);
        mPager.addOnPageChangeListener(this);
        mPagerAdapter = new NumberFragmentPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            MaterialRippleLayout.on(mAddButton)
                    .rippleColor(Color.BLACK)
                    .create();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScrummdApplication.register(mPresenter);
    }

    @Override
    protected void onPause() {
        ScrummdApplication.unregister(mPresenter);
        super.onPause();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mPresenter.onDrag(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void showSettings() {
        DialogFragment fragment = SettingsFragment.newInstance(mPopupFragmentTextColor);
        fragment.show(mFragmentManager, "Settings");
        startSettingsAnimation();
    }

    @Override
    public void showEstimatePicker() {
        int position = getPopupButtonPosition();
        PopupFragment popupFragment = PopupFragment.newInstance(position, mPopupFragmentTextColor);
        popupFragment.show(mFragmentManager, "YO");
        startFabAnimation();
    }

    @Override
    public void changeEstimate(String estimate) {
        int index = mPagerAdapter.getIndex(estimate);
        mPager.setCurrentItem(index);
    }

    @Override
    public void setTheming(int background, int status, int popup, int button) {
        setBackgroundColor(background);
        setStatusBarColor(status);
        setPopupEstimateCircleColor(popup);
        setPopupButtonIconColor(button);
    }

    @Override
    public void onEstimatePickerOpened() {

    }

    @Override
    public void onEstimatePickerClosed() {
        setPopupButtonIcon(false);
    }

    @Override
    public void onNumberModelChanged() {
        mPagerAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(0);
    }

    @Override
    public void onSettingsOpened() {

    }

    @Override
    public void onSettingsClosed() {

    }

    private void setPopupButtonIcon(boolean popupOpen) {
        if (popupOpen) {
            mAddButton.setImageResource(R.drawable.ic_check);
            revertFabAnimation();
        } else {
            mAddButton.setImageResource(R.drawable.ic_dots_vertical);
        }
    }

    private void setBackgroundColor(int color) {
        mPager.setBackgroundColor(color);
    }

    private void setPopupButtonIconColor(int color) {
        mAddButton.setColorFilter(color);
    }

    private void setPopupEstimateCircleColor(int color) {
        mPopupFragmentTextColor = (color);
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mWindow.setStatusBarColor(color);
        }
    }

    private int getPopupButtonPosition() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return (int) (mAddButton.getPaddingBottom() + mAddButton.getHeight() + mAddButton.getY());
        } else {
            return mAddButton.getPaddingBottom() + mAddButton.getHeight();
        }
    }

    private void startFabAnimation() {
        Animation animation = new PopupButtonAnimation(mAddButton);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setPopupButtonIcon(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mAddButton.startAnimation(animation);
    }

    private void startSettingsAnimation() {
        Animation animation = new SettingsButtonAnimation(mSettingsView);
        mSettingsView.startAnimation(animation);
    }

    private void revertFabAnimation() {
        PopupButtonAnimation animation = (PopupButtonAnimation) mAddButton.getAnimation();
        animation.revertAnimation();
    }

}
