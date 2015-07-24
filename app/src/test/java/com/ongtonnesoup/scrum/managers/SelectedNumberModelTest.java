package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.ScrummdApplication;
import com.ongtonnesoup.scrum.models.SelectedNumberModel;
import com.ongtonnesoup.scrum.models.numbers.FibonacciNumberModel;
import com.ongtonnesoup.scrum.models.numbers.ScrumNumberModel;
import com.ongtonnesoup.scrum.proxys.PersistenceProxy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
    private PersistenceProxy mPersistenceProxy;
    private SelectedNumberModel mSelectedNumberModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mSelectedNumberModel = new SelectedNumberModel(mPersistenceProxy);
    }

    @Test
    public void testSetsCurrentModelToPersistedModel() {
        when(mPersistenceProxy.load(SelectedNumberModel.KEY_MODEL)).thenReturn(new FibonacciNumberModel().getName());

        mSelectedNumberModel = new SelectedNumberModel(mPersistenceProxy);

        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), new FibonacciNumberModel().getName());
    }

    @Test
    public void testSetsCurrentModelToScrumIfNoPersistedModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), new ScrumNumberModel().getName());
    }

    @Test
    public void testSetCurrentModelReturnsTrueAndPersistsIfNewModelDoesNotEqualCurrentModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), new ScrumNumberModel().getName());

        boolean result = mSelectedNumberModel.setCurrentModel(new FibonacciNumberModel().getName());

        assertTrue(result);
        verify(mPersistenceProxy).persist(anyString(), anyString());
    }

    @Test
    public void testSetCurrentModelReturnsFalseIfNewModelEqualsCurrentModel() {
        assertEquals(mSelectedNumberModel.getCurrentModel().getName(), new ScrumNumberModel().getName());

        boolean result = mSelectedNumberModel.setCurrentModel(new ScrumNumberModel().getName());

        assertFalse(result);
        verify(mPersistenceProxy, never()).persist(anyString(), anyString());
    }

}