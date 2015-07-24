package com.ongtonnesoup.scrummd.domain.models.theme;

public class ColourTheme<T> {

    private T mPrimaryColor;
    private T mSecondaryColor;
    private T mAccentColor;

    public T getPrimaryColor() {
        return mPrimaryColor;
    }

    public T getSecondaryColor() {
        return mSecondaryColor;
    }

    public T getAccentColor() {
        return mAccentColor;
    }

    public void setPrimary(T value) {
        mPrimaryColor = value;
    }

    public void setSecondary(T value) {
        mSecondaryColor = value;
    }

    public void setAccent(T value) {
        mAccentColor = value;
    }

}
