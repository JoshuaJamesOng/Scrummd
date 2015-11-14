package com.ongtonnesoup.scrummd.presentation.models;

public interface ResourceProxy {

    String getSubmitSuccessMessage();
    String getSubmitErrorMessage();
    int getPrimaryTextColor();
    int getSecondaryTextColor();
    int[] getBackgroundColors();
    int[] getFillColors();
    int[] getStatusBarColors();

}
