package com.ongtonnesoup.scrummd.domain.factorys;

import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

public class ColourThemeFactory<T> {

    public static <T> ColourTheme<T> createColourTheme(T primary, T secondary, T accent) {
        ColourTheme<T> theme = new ColourTheme<>();
        theme.setPrimary(primary);
        theme.setSecondary(secondary);
        theme.setAccent(accent);
        return theme;
    }

}
