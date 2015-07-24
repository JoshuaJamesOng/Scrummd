package com.ongtonnesoup.scrummd.domain.models.theme;

public class ColourTheme {

    private int mPrimaryColor;
    private int mSecondaryColor;
    private int mAccentColor;

    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    public int getSecondaryColor() {
        return mSecondaryColor;
    }

    public int getAccentColor() {
        return mAccentColor;
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

}
