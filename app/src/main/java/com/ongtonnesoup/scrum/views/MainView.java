package com.ongtonnesoup.scrum.views;

public interface MainView {

    void showSettings();
    void showEstimatePicker();
    void changeEstimate(String estimate);
    void setTheming(int background, int status, int popup, int button);

    void onEstimatePickerOpened();
    void onEstimatePickerClosed();
    void onSettingsOpened();
    void onSettingsClosed();

}
