package com.ongtonnesoup.scrum;

import android.app.Application;

import com.ongtonnesoup.scrum.utils.InjectionModule;
import com.squareup.otto.Bus;

import dagger.ObjectGraph;

public class ScrummdApplication extends Application {

    private static Bus mBus;
    private static ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        mObjectGraph = ObjectGraph.create(new InjectionModule());
        mBus = new Bus();
    }

    public static void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public static void register(Object object) {
        mBus.register(object);
    }

    public static void unregister(Object object) {
        mBus.unregister(object);
    }

    public static void post(Object object) {
        mBus.post(object);
    }

}
