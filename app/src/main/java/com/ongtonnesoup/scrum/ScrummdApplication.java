package com.ongtonnesoup.scrum;

import android.app.Application;

import com.ongtonnesoup.scrum.observers.ModelChangedObserver;
import com.ongtonnesoup.scrum.utils.InjectionModule;
import com.ongtonnesoup.scrummd.presentation.PresentationModule;
import com.squareup.otto.Bus;

import dagger.ObjectGraph;

public class ScrummdApplication extends Application {

    private static Bus mBus;
    private static ObjectGraph mObjectGraph;
    private ModelChangedObserver mObserver;

    public static ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    public static void inject(Object object) {
        getObjectGraph().inject(object);
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

    @Override
    public void onCreate() {
        mObjectGraph = ObjectGraph.create(new InjectionModule(getApplicationContext()));
        mBus = new Bus();
        mObserver = new ModelChangedObserver();
        register(mObserver);
        PresentationModule.setObjectGraph(mObjectGraph);
        PresentationModule.setBus(mBus);
    }

}
