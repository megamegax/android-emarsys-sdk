package com.emarsys.mobileengage.util

import com.emarsys.core.endpoint.ServiceEndpointProvider
import com.emarsys.core.request.model.RequestModel
import com.emarsys.testUtil.TimeoutUtils
import com.emarsys.testUtil.mockito.whenever
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.kotlintest.shouldBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import java.net.URL

class RequestModelHelperTest {

    private companion object {
        const val CLIENT_HOST = "https://me-client.eservice.emarsys.net/v3"
        const val CLIENT_BASE = "$CLIENT_HOST/apps/%s/client"
        const val EVENT_HOST = "https://mobile-events.eservice.emarsys.net"
        const val EVENT_BASE = "$EVENT_HOST/v3/apps/%s/client/events"
        const val EVENT_BASE_V4 = "$EVENT_HOST/v4/apps/%s/client/events"
        const val INLINE_IN_APP_V4 = "$EVENT_HOST/v4/apps/%s/inline-messages"
        const val INLINE_IN_APP_V3 = "$EVENT_HOST/v3/apps/%s/inline-messages"
        const val INBOX_HOST = "https://mobile-events.eservice.emarsys.net/v3"
        const val INBOX_BASE = "$INBOX_HOST/apps/%s/inbox"
        const val REMOTE_CONFIG_HOST = "https://mobile-sdk-config.gservice.emarsys.net"
    }

    private lateinit var mockClientServiceProvider: ServiceEndpointProvider
    private lateinit var mockEventServiceProvider: ServiceEndpointProvider
    private lateinit var mockMessageInboxServiceProvider: ServiceEndpointProvider
    private lateinit var mockRequestModel: RequestModel
    private lateinit var requestModelHelper: RequestModelHelper

    @Before
    fun setUp() {
        mockRequestModel = mock()

        mockClientServiceProvider = mock {
            on { provideEndpointHost() } doReturn CLIENT_HOST
        }
        mockEventServiceProvider = mock {
            on { provideEndpointHost() } doReturn EVENT_HOST
        }
        mockMessageInboxServiceProvider = mock {
            on { provideEndpointHost() } doReturn INBOX_HOST
        }

        requestModelHelper = RequestModelHelper(mockClientServiceProvider, mockEventServiceProvider, mockMessageInboxServiceProvider)
    }

    @Rule
    @JvmField
    val timeout: TestRule = TimeoutUtils.timeoutRule

    @Test
    fun testIsMobileEngageRequest_true_whenItIsMobileEngageClient() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(CLIENT_BASE))
        }
        val result = requestModelHelper.isMobileEngageRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsMobileEngageRequest_true_whenItIsMobileEngageEvent() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(EVENT_BASE))
        }
        val result = requestModelHelper.isMobileEngageRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsMobileEngageRequest_true_whenItIsMobileEngageEventV4() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(EVENT_BASE_V4))
        }
        val result = requestModelHelper.isMobileEngageRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsMobileEngageRequest_true_whenItIsMobileEngageMessageInbox() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(INBOX_BASE))
        }
        val result = requestModelHelper.isMobileEngageRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsMobileEngageRequest_false_whenItIsNotMobileEngage() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL("https://not-mobile-engage.com"))
        }
        val result = requestModelHelper.isMobileEngageRequest(mockRequestModel)

        result shouldBe false
    }

    @Test
    fun testIsCustomEvent_V3_true_whenItIsCustomEventV3Event() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(EVENT_BASE))
        }
        val result = requestModelHelper.isCustomEvent(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsRemoteConfig_true_whenItIsRemoteConfigUrl() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(REMOTE_CONFIG_HOST))
        }
        val result = requestModelHelper.isRemoteConfigRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsCustomEvent_V3_false_whenItIsNotCustomEventV3Event() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(CLIENT_BASE))
        }
        val result = requestModelHelper.isCustomEvent(mockRequestModel)

        result shouldBe false
    }

    @Test
    fun testIsRefreshContactTokenRequest_true_whenItIsRefreshContactTokenRequest() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL("$CLIENT_BASE/contact-token"))
        }
        val result = requestModelHelper.isRefreshContactTokenRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsRefreshContactTokenRequest_false_whenItIsNotRefreshContactTokenRequest() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL("$CLIENT_BASE/contact"))
        }
        val result = requestModelHelper.isRefreshContactTokenRequest(mockRequestModel)

        result shouldBe false
    }

    @Test
    fun testIsMobileEngageClientRequest_false_whenIsNotClientEndpoint() {

        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL("$EVENT_BASE/apps"))
        }
        val result = requestModelHelper.isMobileEngageSetContactRequest(mockRequestModel)

        result shouldBe false
    }

    @Test
    fun testIsMobileEngageClientRequest_true_whenClientEndpoint() {

        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL("$CLIENT_BASE/contact"))
        }
        val result = requestModelHelper.isMobileEngageSetContactRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsInlineInAppRequest_false_whenItIsNotInlineInAppRequest_V4() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(EVENT_BASE_V4))
        }
        val result = requestModelHelper.isInlineInAppRequest(mockRequestModel)

        result shouldBe false
    }

    @Test
    fun testIsInlineInAppRequest_true_whenItIsInlineInAppRequest_V4() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(INLINE_IN_APP_V4))
        }
        val result = requestModelHelper.isInlineInAppRequest(mockRequestModel)

        result shouldBe true
    }

    @Test
    fun testIsInlineInAppRequest_true_whenItIsInlineInAppRequest_V3() {
        val mockRequestModel = mock(RequestModel::class.java).apply {
            whenever(url).thenReturn(URL(INLINE_IN_APP_V3))
        }
        val result = requestModelHelper.isInlineInAppRequest(mockRequestModel)

        result shouldBe true
    }

}