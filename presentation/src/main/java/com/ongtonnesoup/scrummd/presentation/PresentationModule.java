package com.ongtonnesoup.scrummd.presentation;

import com.squareup.otto.Bus;

import dagger.ObjectGraph;

public class PresentationModule {

    private static ObjectGraph mObjectGraph;
    private static Bus mBus;

    public static void setObjectGraph(ObjectGraph objectGraph) {
        mObjectGraph = objectGraph;
    }

    public static void setBus(Bus bus) {
        mBus = bus;
    }

    public static void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public static void post(Object object) {
        mBus.post(object);
    }

}
