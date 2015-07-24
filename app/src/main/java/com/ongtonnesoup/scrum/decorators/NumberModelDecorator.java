package com.ongtonnesoup.scrum.decorators;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.managers.NumberModelManager;
import com.ongtonnesoup.scrum.managers.ResourceManager;

import javax.inject.Inject;

public class NumberModelDecorator {

    private static final String RESOURCE_IDENTIFIER = "R.drawable.";

    @Inject
    protected NumberModelManager mNumberModelManager;
    @Inject
    protected ResourceManager mResources;

    public NumberModelDecorator() {
        ScrummdApplication.inject(this);
    }

    public String getNumber(int index) {
        String number = mNumberModelManager.getCurrentModel().getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            number = null;
        }

        return number;
    }

    public int getResourceIdentifier(int index) {
        int resourceId = 0;
        String number = mNumberModelManager.getCurrentModel().getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            String resourceName = number.substring(RESOURCE_IDENTIFIER.length());
            resourceId = mResources.findResourceIdentifier(resourceName);
        }

        return resourceId;
    }
}
