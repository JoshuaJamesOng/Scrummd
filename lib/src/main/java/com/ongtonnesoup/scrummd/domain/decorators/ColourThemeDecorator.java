package com.ongtonnesoup.scrummd.domain.decorators;

import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

public class ColourThemeDecorator {

    private final ColourTheme mColourTheme;

    public ColourThemeDecorator(ColourTheme colourTheme) {
        mColourTheme = colourTheme;
    }

    public int getBackgroundColor() {
        return mColourTheme.getPrimaryColor();
    }

    public int getCircleColor() {
        return mColourTheme.getAccentColor();
    }

    public int getStatusBarColor() {
        return mColourTheme.getSecondaryColor();
    }

    public int getTextColor() {
        return mColourTheme.getSecondaryColor();
    }
}
