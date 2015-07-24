package com.ongtonnesoup.scrum.managers;

import com.ongtonnesoup.scrum.ScrummdApplication;
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
public class NumberModelManagerTest {

    @Mock
    private PersistenceProxy mPersistenceProxy;
    private NumberModelManager mNumberModelManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mNumberModelManager = new NumberModelManager(mPersistenceProxy);
    }

    @Test
    public void testSetsCurrentModelToPersistedModel() {
        when(mPersistenceProxy.load(NumberModelManager.KEY_MODEL)).thenReturn(new FibonacciNumberModel().getName());

        mNumberModelManager = new NumberModelManager(mPersistenceProxy);

        assertEquals(mNumberModelManager.getCurrentModel().getName(), new FibonacciNumberModel().getName());
    }

    @Test
    public void testSetsCurrentModelToScrumIfNoPersistedModel() {
        assertEquals(mNumberModelManager.getCurrentModel().getName(), new ScrumNumberModel().getName());
    }

    @Test
    public void testSetCurrentModelReturnsTrueAndPersistsIfNewModelDoesNotEqualCurrentModel() {
        assertEquals(mNumberModelManager.getCurrentModel().getName(), new ScrumNumberModel().getName());

        boolean result = mNumberModelManager.setCurrentModel(new FibonacciNumberModel().getName());

        assertTrue(result);
        verify(mPersistenceProxy).persist(anyString(), anyString());
    }

    @Test
    public void testSetCurrentModelReturnsFalseIfNewModelEqualsCurrentModel() {
        assertEquals(mNumberModelManager.getCurrentModel().getName(), new ScrumNumberModel().getName());

        boolean result = mNumberModelManager.setCurrentModel(new ScrumNumberModel().getName());

        assertFalse(result);
        verify(mPersistenceProxy, never()).persist(anyString(), anyString());
    }

}