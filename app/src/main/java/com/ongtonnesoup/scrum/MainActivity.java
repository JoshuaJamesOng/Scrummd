package com.ongtonnesoup.scrum;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import com.balysv.materialripple.MaterialRippleLayout;
import com.ongtonnesoup.scrum.adapters.NumberFragmentPagerAdapter;
import com.ongtonnesoup.scrum.animations.PopupButtonAnimation;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.events.PopupClosed;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.add_button)
    protected FloatingActionButton mAddButton;
    private FragmentManager mFragmentManager;
    private ColourThemeManager mColourThemeManager;
    private ViewPager mPager;
    private NumberFragmentPagerAdapter mPagerAdapter;
    private final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Window mWindow;
    private int mPopupFragmentTextColor;
    private int[] mSecondaryColors;
    private int[] mAccentColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrummdApplication.inject(this);
        ButterKnife.inject(this);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getPopupButtonPosition();
                PopupFragment popupFragment = PopupFragment.newInstance(position, mPopupFragmentTextColor);
                popupFragment.show(mFragmentManager, "YO");
                startFabAnimation();
            }
        });

        mWindow = getWindow();

        mColourThemeManager = new ColourThemeManager();
        mFragmentManager = getSupportFragmentManager();

        mSecondaryColors = mColourThemeManager.getBackgroundColors();
        mAccentColors = mColourThemeManager.getStatusBarColors();

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
        ScrummdApplication.register(this);
        ScrummdApplication.register(mColourThemeManager);
    }

    @Override
    protected void onPause() {
        ScrummdApplication.unregister(mColourThemeManager);
        ScrummdApplication.unregister(this);
        super.onPause();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int targetPosition = position + 1;
        position = mColourThemeManager.getColorForIndex(position);
        targetPosition = mColourThemeManager.getColorForIndex(targetPosition);

        setBackgroundColor(calculateColor(positionOffset, mSecondaryColors[position], mSecondaryColors[targetPosition]));
        setStatusBarColor(calculateColor(positionOffset, mAccentColors[position], mAccentColors[targetPosition]));
        setPopupEstimateCircleColor(calculateColor(positionOffset, mSecondaryColors[position], mSecondaryColors[targetPosition]));
        setPopupButtonIconColor(calculateColor(positionOffset, mAccentColors[position], mAccentColors[targetPosition]));
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Subscribe
    public void onEstimateChanged(EstimateSelected event) {
        int index = mPagerAdapter.getIndex(event.getNumber());
        mPager.setCurrentItem(index);
    }

    @Subscribe
    public void onPopupClosed(PopupClosed event) {
        setPopupButtonIcon(false);
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

    private int calculateColor(float positionOffset, int start, int end) {
        return (Integer) argbEvaluator.evaluate(positionOffset, start, end);
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

    private void revertFabAnimation() {
        PopupButtonAnimation animation = (PopupButtonAnimation) mAddButton.getAnimation();
        animation.revertAnimation();
    }

}
