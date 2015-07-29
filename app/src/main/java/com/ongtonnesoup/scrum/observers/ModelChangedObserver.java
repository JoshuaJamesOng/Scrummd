package com.ongtonnesoup.scrum.observers;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrummd.presentation.events.ModelChanged;
import com.ongtonnesoup.scrum.proxys.AndroidPersistenceProxy;
import com.ongtonnesoup.scrummd.presentation.interfaces.PersistenceProxy;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class ModelChangedObserver {

    @Inject
    PersistenceProxy mPersistence;

    public ModelChangedObserver() {
        ScrummdApplication.inject(this);
    }

    @Subscribe
    public void onModelChanged(ModelChanged event) {
        if (event != null) {
            mPersistence.persist(AndroidPersistenceProxy.KEY_MODEL, event.getModel());
        }
    }
}
