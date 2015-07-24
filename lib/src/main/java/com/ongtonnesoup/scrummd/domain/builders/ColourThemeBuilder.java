package com.ongtonnesoup.scrummd.domain.builders;

import com.ongtonnesoup.scrummd.domain.models.theme.ColourTheme;

public class ColourThemeBuilder<T> {

    public ColourTheme<T> generateNewColourTheme(T primary, T secondary, T accent) {
        ColourTheme<T> theme = new ColourTheme<>();
        theme.setPrimary(primary);
        theme.setSecondary(secondary);
        theme.setAccent(accent);
        return theme;
    }
    
}
