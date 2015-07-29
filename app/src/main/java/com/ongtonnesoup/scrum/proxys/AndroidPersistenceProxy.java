package com.ongtonnesoup.scrum.proxys;

import android.content.SharedPreferences;

import com.ongtonnesoup.scrummd.domain.models.numbers.NumberModel;
import com.ongtonnesoup.scrummd.presentation.interfaces.PersistenceProxy;

public class AndroidPersistenceProxy implements PersistenceProxy {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String KEY_MODEL = "KEY_MODEL";

    private final SharedPreferences mSharedPrefences;

    public AndroidPersistenceProxy(SharedPreferences sharedPreferences) {
        mSharedPrefences = sharedPreferences;
    }

    @Override
    public boolean persist(String key, String value) {
        return mSharedPrefences.edit().putString(key, value).commit();
    }

    @Override
    public boolean persist(String key, NumberModel model) {
        return persist(key, model.getName());
    }

    @Override
    public String loadModel() {
        return mSharedPrefences.getString(KEY_MODEL, null);
    }
}
