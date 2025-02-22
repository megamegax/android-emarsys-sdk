package com.emarsys.deeplink

import android.app.Activity
import android.content.Intent
import com.emarsys.core.api.result.CompletionListener
import com.emarsys.core.di.DependencyInjection
import com.emarsys.di.FakeDependencyContainer
import com.emarsys.mobileengage.deeplink.DeepLinkInternal
import com.emarsys.testUtil.IntegrationTestUtils
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeepLinkTest {
    private lateinit var mockActivity: Activity
    private lateinit var mockCompletionListener: CompletionListener
    private lateinit var mockDeepLinkInternal: DeepLinkInternal
    private lateinit var deeplinkApi: DeepLink


    @Before
    fun setUp() {
        mockActivity = mock()
        mockCompletionListener = mock()
        mockDeepLinkInternal = mock()
        deeplinkApi = DeepLink()

        DependencyInjection.setup(FakeDependencyContainer(
                deepLinkInternal = mockDeepLinkInternal,
                loggingDeepLinkInternal = mockDeepLinkInternal))
    }
    @After
    fun tearDown() {
        IntegrationTestUtils.tearDownEmarsys()
    }

    @Test
    fun testDeepLinkApi_delegatesToInternal() {
        val intent = Intent()
        deeplinkApi.trackDeepLinkOpen(mockActivity, intent, mockCompletionListener)
        verify(mockDeepLinkInternal).trackDeepLinkOpen(mockActivity, intent, mockCompletionListener)
    }
}