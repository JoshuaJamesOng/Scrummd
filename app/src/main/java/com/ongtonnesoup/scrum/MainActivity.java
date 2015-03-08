package com.ongtonnesoup.scrum;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.managers.ColourThemeManager;
import com.ongtonnesoup.scrum.managers.FragmentManager;
import com.ongtonnesoup.scrum.managers.GestureManager;
import com.ongtonnesoup.scrum.models.ColourTheme;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @Inject
    ColourTheme mColourTheme;
    @InjectView(R.id.add_button)
    View mAddButton;
    @Inject
    GestureManager mGestureManager;
    private FragmentManager mFragmentManager;
    private ColourThemeManager mColourThemeManager;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrummdApplication.inject(this);
        ButterKnife.inject(this);

        mFragmentManager = new FragmentManager(getFragmentManager());
        mColourThemeManager = new ColourThemeManager(getResources());
        mGestureDetector = new GestureDetector(getApplicationContext(), mGestureManager);
        mGestureDetector.setOnDoubleTapListener(mGestureManager);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.toggleSettingsFragment();
            }
        });

        mFragmentManager.createNumbersFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScrummdApplication.register(this);
        ScrummdApplication.register(mFragmentManager);
        ScrummdApplication.register(mColourThemeManager);
    }

    @Override
    protected void onPause() {
        ScrummdApplication.unregister(mColourThemeManager);
        ScrummdApplication.unregister(mFragmentManager);
        ScrummdApplication.unregister(this);
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
