package com.ongtonnesoup.scrum;

import android.animation.ArgbEvaluator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.views.adapters.NumberFragmentPagerAdapter;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.add_button)
    View mAddButton;
    FragmentManager mFragmentManager;
    private ColourThemeManager mColourThemeManager;
    private ViewPager mPager;
    private NumberFragmentPagerAdapter mPagerAdapter;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private Window mWindow;
    private PopupFragment mPopupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrummdApplication.inject(this);
        ButterKnife.inject(this);

        mWindow = getWindow();

        mColourThemeManager = new ColourThemeManager();

        mFragmentManager = getSupportFragmentManager();
        mPopupFragment = PopupFragment.newInstance();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupFragment.show(mFragmentManager, "YO");
            }
        });


        mPager = (ViewPager) findViewById(R.id.fragment_container);
        mPager.setOnPageChangeListener(this);
        mPagerAdapter = new NumberFragmentPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
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

    @Subscribe
    public void onEstimateChanged(EstimateSelected event) {
        int index = mPagerAdapter.getIndex(event.getNumber());
        mPager.setCurrentItem(index);
    }

    private void updateStatusBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mWindow.setStatusBarColor(color);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int[] background = mColourThemeManager.getBackgroundColors();
        int[] status = mColourThemeManager.getStatusBarColors();
        int[] fill = mColourThemeManager.getBackgroundColors();
        if (position < (mPagerAdapter.getCount() - 1) && position < (background.length - 1)) {
            mPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, background[position], background[position + 1]));
            updateStatusBar((Integer) argbEvaluator.evaluate(positionOffset, status[position], status[position + 1]));
            mPopupFragment.setTextColor((Integer) argbEvaluator.evaluate(positionOffset, fill[position], fill[position + 1]));
        } else {
            mPager.setBackgroundColor(background[background.length - 1]);
            updateStatusBar(status[status.length - 1]);
            mPopupFragment.setTextColor(fill[fill.length - 1]);
        }

    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
