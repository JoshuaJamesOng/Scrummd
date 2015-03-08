package com.ongtonnesoup.scrum.managers;

import android.app.Fragment;
import android.app.FragmentTransaction;

import com.ongtonnesoup.scrum.R;
import com.ongtonnesoup.scrum.events.EstimateSelected;
import com.ongtonnesoup.scrum.events.TouchGesture;
import com.ongtonnesoup.scrum.fragments.NumberFragment;
import com.ongtonnesoup.scrum.fragments.PopupFragment;
import com.ongtonnesoup.scrum.models.TouchType;
import com.squareup.otto.Subscribe;

public class FragmentManager {

    private final android.app.FragmentManager mFragmentManager;
    private boolean mPopupVisible;

    public FragmentManager(android.app.FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public boolean popupVisible() {
        return mPopupVisible;
    }

    void replaceFragment(Fragment fragment, String tag, boolean addToBackstack) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, fragment, tag);
        if (addToBackstack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    void addFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, tag).addToBackStack(null).commit();
    }

    void removeFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().detach(fragment).commit();
        mFragmentManager.popBackStack();
    }

    public void createNumbersFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag(NumberFragment.TAG);
        if (fragment == null) {
            replaceFragment(new NumberFragment(), NumberFragment.TAG, false);
        } else {
            replaceFragment(fragment, NumberFragment.TAG, false);
        }
    }

    public void toggleSettingsFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag(PopupFragment.TAG);
        if (fragment == null || !fragment.isAdded()) {
            addFragment(new PopupFragment(), PopupFragment.TAG);
            mPopupVisible = true;
        } else {
            removeFragment(fragment);
            mPopupVisible = false;
        }
    }

    @Subscribe
    public void dismissSettingsMenu(EstimateSelected event) {
        toggleSettingsFragment();
    }

    @Subscribe
    public void handleTouchEvent(TouchGesture event) {

        if (event.getTouch().equals(TouchType.TAP) && mPopupVisible) {
            toggleSettingsFragment();
        }
    }

}
