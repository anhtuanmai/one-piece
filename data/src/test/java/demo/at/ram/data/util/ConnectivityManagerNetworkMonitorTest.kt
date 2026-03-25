package demo.at.ram.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import app.cash.turbine.test
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConnectivityManagerNetworkMonitorTest {

    private lateinit var context: Context
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var networkMonitor: ConnectivityManagerNetworkMonitor
    private lateinit var capturedCallback: ConnectivityManager.NetworkCallback

    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        // Mock everything with MockK
        context = mockk(relaxed = true)
        connectivityManager = mockk(relaxed = true)

        // Mock NetworkRequest.Builder
        val mockBuilder = mockk<NetworkRequest.Builder>(relaxed = true)
        val mockRequest = mockk<NetworkRequest>(relaxed = true)
        every { mockBuilder.addCapability(any()) } returns mockBuilder
        every { mockBuilder.build() } returns mockRequest

        mockkConstructor(NetworkRequest.Builder::class)
        every { anyConstructed<NetworkRequest.Builder>().addCapability(any()) } returns mockBuilder
        every { anyConstructed<NetworkRequest.Builder>().build() } returns mockRequest

        // Mock getSystemService properly
        every {
            context.getSystemService(Context.CONNECTIVITY_SERVICE)
        } returns connectivityManager

        // Capture callback
        every {
            connectivityManager.registerNetworkCallback(any<NetworkRequest>(), any<ConnectivityManager.NetworkCallback>())
        } answers {
            capturedCallback = secondArg()
        }

        networkMonitor = ConnectivityManagerNetworkMonitor(
            context = context,
            ioDispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }


        @Test
                fun testNullConnectivityManager() = runTest {
            // Given
            val nullContext = mockk<Context>(relaxed = true)
            every {
                nullContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            } returns null

            val monitor = ConnectivityManagerNetworkMonitor(
                context = nullContext,
                ioDispatcher = testDispatcher
            )

            // When
            monitor.isOnline.test {
                // Then
                assertFalse(awaitItem())
                awaitComplete()
            }
        }

        @Test
                fun testInitiallyConnected() = runTest {
            // Given
            mockStartConnectionState(true)

            // When
            networkMonitor.isOnline.test {
                assertTrue(awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        @Test
                fun testInitiallyDisconnected() = runTest {
            // Given
            mockStartConnectionState(false)

            // When
            networkMonitor.isOnline.test {
                assertFalse(awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }


        @Test
                fun testNetworkBecomesAvailable() = runTest {
            // Given
            mockStartConnectionState(false)

            networkMonitor.isOnline.test {
                assertFalse(awaitItem())

                // When
                val mockNetwork = mockk<Network>()
                capturedCallback.onAvailable(mockNetwork)

                // Then
                assertTrue(awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        @Test
                fun testNetworkIsLost() = runTest {
            // Given
            mockStartConnectionState(false)

            networkMonitor.isOnline.test {
                assertFalse(awaitItem())

                // Add network
                val mockNetwork = mockk<Network>()
                capturedCallback.onAvailable(mockNetwork)
                assertTrue(awaitItem())

                // When - lose network
                capturedCallback.onLost(mockNetwork)

                // Then
                assertFalse(awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }


        @Test
                fun testMultipleNetworksAvailable() = runTest {
            // Given
            mockStartConnectionState(false)

            networkMonitor.isOnline.test {
                assertFalse(awaitItem())

                // Add first network
                val network1 = mockk<Network>()
                capturedCallback.onAvailable(network1)
                assertTrue(awaitItem())

                // Add second network
                val network2 = mockk<Network>()
                capturedCallback.onAvailable(network2)
                assertTrue(awaitItem())

                // Lose first network
                capturedCallback.onLost(network1)

                // Then - still true because network2 exists
                assertTrue(awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }

        @Test
                fun testAllNetworksLost() = runTest {
            // Given
            mockStartConnectionState(false)

            networkMonitor.isOnline.test {
                assertFalse(awaitItem())

                // Add two networks
                val network1 = mockk<Network>()
                val network2 = mockk<Network>()
                capturedCallback.onAvailable(network1)
                assertTrue(awaitItem())
                capturedCallback.onAvailable(network2)
                assertTrue(awaitItem())

                // Lose both
                capturedCallback.onLost(network1)
                assertTrue(awaitItem())

                capturedCallback.onLost(network2)

                // Then
                assertFalse(awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }


        @Test
                fun testCallbackRegistration() = runTest {
            networkMonitor.isOnline.test {
                awaitItem()
                cancelAndIgnoreRemainingEvents()
            }

            verify {
                connectivityManager.registerNetworkCallback(
                    any<NetworkRequest>(),
                    any<ConnectivityManager.NetworkCallback>()
                )
            }
        }

        @Test
                fun testCallbackUnregistration() = runTest {
            networkMonitor.isOnline.test {
                awaitItem()
                cancel()
            }

            advanceUntilIdle()

            verify {
                connectivityManager.unregisterNetworkCallback(capturedCallback)
            }
        }
    
    fun mockStartConnectionState(isConnected : Boolean) {
        if (!isMockKMock(ConnectivityManagerHelper)) {
            mockkObject(ConnectivityManagerHelper)
            every { ConnectivityManagerHelper.isCurrentlyConnected(any()) } returns isConnected
        } else {
            throw RuntimeException("ConnectivityManagerHelper is mocked already!")
        }
    }
}
