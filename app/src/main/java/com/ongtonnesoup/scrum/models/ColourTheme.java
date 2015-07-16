package com.ongtonnesoup.scrum.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ColourTheme {

    private int mPrimaryColor;
    private int mSecondaryColor;
    private int mAccentColor;

    public ColourTheme() {

    }

    public ColourTheme(Parcel in) {
        int[] data = new int[4];
        in.readIntArray(data);
        this.mPrimaryColor = data[0];
        this.mSecondaryColor = data[1];
        this.mAccentColor = data[2];
    }

    public void setPrimary(int value) {
        mPrimaryColor = value;
    }

    public void setSecondary(int value) {
        mSecondaryColor = value;
    }

    public void setAccent(int value) {
        mAccentColor = value;
    }

    public int getBackgroundColor() {
        return mPrimaryColor;
    }

    public int getCircleColor() {
        return mAccentColor;
    }

    public int getStatusBarColor() {
        return mSecondaryColor;
    }

    public int getTextColor() {
        return mSecondaryColor;
    }

}
