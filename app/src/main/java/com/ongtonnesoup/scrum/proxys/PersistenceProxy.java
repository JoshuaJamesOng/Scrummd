package com.ongtonnesoup.scrum.proxys;

import android.content.SharedPreferences;

public class PersistenceProxy {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";

    private final SharedPreferences mSharedPrefences;

    public PersistenceProxy(SharedPreferences sharedPreferences) {
        mSharedPrefences = sharedPreferences;
    }

    public boolean persist(String key, String value) {
        return mSharedPrefences.edit().putString(key, value).commit();
    }

    public String load(String key) {
        return mSharedPrefences.getString(key, null);
    }
}
