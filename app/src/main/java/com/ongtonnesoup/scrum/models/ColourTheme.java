package com.ongtonnesoup.scrum.models;

public class ColourTheme {

    private int mPrimaryColor;
    private int mSecondaryColor;
    private int mAccentColor;

    public ColourTheme() {

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
