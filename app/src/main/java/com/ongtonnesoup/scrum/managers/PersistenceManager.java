package com.ongtonnesoup.scrum.managers;

import android.content.SharedPreferences;

public class PersistenceManager {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";

    private final SharedPreferences mSharedPrefences;

    public PersistenceManager(SharedPreferences sharedPreferences) {
        mSharedPrefences = sharedPreferences;
    }

    public boolean persist(String key, String value) {
        return mSharedPrefences.edit().putString(key, value).commit();
    }

    public String load(String key) {
        return mSharedPrefences.getString(key, null);
    }
}
