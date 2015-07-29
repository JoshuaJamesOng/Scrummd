package com.ongtonnesoup.scrum.decorators;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.proxys.AndroidResourceProxy;
import com.ongtonnesoup.scrummd.presentation.models.ResourceProxy;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;

import javax.inject.Inject;

public class NumberModelDecorator {

    private static final String RESOURCE_IDENTIFIER = "R.drawable.";

    @Inject
    protected SelectedNumberModel mSelectedNumberModel;
    @Inject
    protected ResourceProxy mResources;

    public NumberModelDecorator() {
        ScrummdApplication.inject(this);
    }

    public String getNumber(int index) {
        String number = mSelectedNumberModel.getCurrentModel().getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            number = null;
        }

        return number;
    }

    public int getResourceIdentifier(int index) {
        int resourceId = 0;
        String number = mSelectedNumberModel.getCurrentModel().getNumber(index);

        if (number.contains(RESOURCE_IDENTIFIER)) {
            String resourceName = number.substring(RESOURCE_IDENTIFIER.length());
            resourceId = ((AndroidResourceProxy) mResources).findResourceIdentifier(resourceName);
        }

        return resourceId;
    }
}
