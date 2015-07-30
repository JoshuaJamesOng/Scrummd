package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrummd.domain.facades.NumberModelFacade;
import com.ongtonnesoup.scrummd.domain.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrummd.domain.models.numbers.ScrumNumberModel;
import com.ongtonnesoup.scrummd.domain.models.numbers.ShirtNumberModel;
import com.ongtonnesoup.scrummd.presentation.interfaces.PersistenceProxy;
import com.ongtonnesoup.scrummd.presentation.models.SelectedNumberModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@PrepareForTest(ScrummdApplication.class)
@RunWith(PowerMockRunner.class)
public class SelectedNumberModelTest {

    @Mock
    private PersistenceProxy mAndroidPersistenceProxy;
    @Mock
    private NumberModelFacade mNumberModelFacade;
    @Mock
    private ScrumNumberModel mScrumNumberModel;

    private SelectedNumberModel mSelectedNumberModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mNumberModelFacade.getModels()).thenReturn(Arrays.asList(mScrumNumberModel, new FibonacciNumberModel(), new ShirtNumberModel()));
        when(mNumberModelFacade.getDefaultModel()).thenReturn(mScrumNumberModel);
        when(mScrumNumberModel.getName()).thenReturn("Dummy");

        mSelectedNumberModel = new SelectedNumberModel(mAndroidPersistenceProxy, mNumberModelFacade);
    }

    @Test
    public void testSetsCurrentModelToPersistedModel() {
        when(mAndroidPersistenceProxy.loadModel()).thenReturn(new FibonacciNumberModel().getName());

        mSelectedNumberModel = new SelectedNumberModel(mAndroidPersistenceProxy, mNumberModelFacade);

        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), new FibonacciNumberModel().getName());
    }

    @Test
    public void testSetsCurrentModelToScrumIfNoPersistedModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), mScrumNumberModel.getName());
    }

    @Test
    public void testSetCurrentModelReturnsTrueAndPersistsIfNewModelDoesNotEqualCurrentModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), mScrumNumberModel.getName());

        boolean result = mSelectedNumberModel.setCurrentModel(new FibonacciNumberModel());

        assertTrue(result);
    }

    @Test
    public void testSetCurrentModelReturnsFalseIfNewModelEqualsCurrentModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), mScrumNumberModel.getName());

        boolean result = mSelectedNumberModel.setCurrentModel(mScrumNumberModel);

        assertFalse(result);
    }

}