package com.ongtonnesoup.scrum.proxys;

import android.content.SharedPreferences;

import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;

public class PersistenceProxy {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String KEY_MODEL = "KEY_MODEL";

    private final SharedPreferences mSharedPrefences;

    public PersistenceProxy(SharedPreferences sharedPreferences) {
        mSharedPrefences = sharedPreferences;
    }

    public boolean persist(String key, String value) {
        return mSharedPrefences.edit().putString(key, value).commit();
    }

    public boolean persist(String key, NumberModel model) {
        return persist(key, model.getName());
    }

    public String load(String key) {
        return mSharedPrefences.getString(key, null);
    }
}
