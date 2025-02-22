package com.emarsys.geofence

import com.emarsys.core.api.result.CompletionListener
import com.emarsys.core.di.DependencyInjection
import com.emarsys.di.FakeDependencyContainer
import com.emarsys.mobileengage.api.event.EventHandler
import com.emarsys.mobileengage.geofence.GeofenceInternal
import com.emarsys.testUtil.IntegrationTestUtils
import com.emarsys.testUtil.TimeoutUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GeofenceTest {

    private lateinit var geofenceProxy: GeofenceApi
    private lateinit var mockGeofenceInternal: GeofenceInternal

    @Rule
    @JvmField
    val timeout: TestRule = TimeoutUtils.timeoutRule

    @Before
    fun setUp() {
        mockGeofenceInternal = mock()
        val dependencyContainer = FakeDependencyContainer(geofenceInternal = mockGeofenceInternal)

        DependencyInjection.setup(dependencyContainer)

        geofenceProxy = Geofence()
    }

    @After
    fun tearDown() {
        IntegrationTestUtils.tearDownEmarsys()
    }

    @Test
    fun testEnableDelegatesToGeofenceInternalMethod_throughRunnerProxy() {
        geofenceProxy.enable()

        verify(mockGeofenceInternal).enable(null)
    }

    @Test
    fun testEnableDelegatesToGeofenceInternalMethod_withCompletionListener() {
        val mockCompletionListener: CompletionListener = mock()
        geofenceProxy.enable(mockCompletionListener)

        verify(mockGeofenceInternal).enable(mockCompletionListener)
    }

    @Test
    fun testEnableDelegatesToGeofenceInternalMethod_withLambda() {
        val mockCompletionListener: (Throwable?) -> Unit = mock()
        geofenceProxy.enable(mockCompletionListener)

        verify(mockGeofenceInternal).enable(any())
    }

    @Test
    fun testDisableDelegatesToGeofenceInternalMethod() {
        geofenceProxy.disable()

        verify(mockGeofenceInternal).disable()
    }

    @Test
    fun testSetEventHandler() {
        val eventHandler: EventHandler = mock()

        geofenceProxy.setEventHandler(eventHandler)

        verify(mockGeofenceInternal).setEventHandler(eventHandler)
    }

    @Test
    fun testIsEnabledDelegatesToGeofenceInternalMethod() {
        geofenceProxy.isEnabled()

        verify(mockGeofenceInternal).isEnabled()
    }
}