package com.ongtonnesoup.scrummd.domain.decorators;

import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

public class ColourThemeDecorator<T> {

    private final ColourTheme<T> mColourTheme;

    public ColourThemeDecorator(ColourTheme<T> colourTheme) {
        mColourTheme = colourTheme;
    }

    public T getBackgroundColor() {
        return mColourTheme.getPrimaryColor();
    }

    public T getCircleColor() {
        return mColourTheme.getAccentColor();
    }

    public T getStatusBarColor() {
        return mColourTheme.getSecondaryColor();
    }

    public T getTextColor() {
        return mColourTheme.getSecondaryColor();
    }
}
