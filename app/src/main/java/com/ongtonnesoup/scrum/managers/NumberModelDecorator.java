package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.numbers.NumberModel;

import javax.inject.Inject;

public class NumberModelDecorator {

    private static final String RESOURCE_IDENTIFIER = "R.drawable.";

    @Inject
    protected NumberModel mNumberModel;
    @Inject
    protected ResourceManager mResources;

    public NumberModelDecorator() {
        ScrummdApplication.inject(this);
    }

    public String getNumber(int index) {
        String number = mNumberModel.getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            number = null;
        }

        return number;
    }

    public int getResourceIdentifier(int index) {
        int resourceId = 0;
        String number = mNumberModel.getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            String resourceName = number.substring(RESOURCE_IDENTIFIER.length());
            resourceId = mResources.findResourceIdentifier(resourceName);
        }

        return resourceId;
    }
}
