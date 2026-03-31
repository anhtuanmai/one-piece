package demo.at.ram.presentation

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.AfterClass
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(org.robolectric.RobolectricTestRunner::class)
@Config(application = RobolectricExtensionSelfTest.MyTestApplication::class)
class RobolectricExtensionSelfTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldInitializeAndBindApplicationAndCallOnCreate() {
        val application = ApplicationProvider.getApplicationContext<Context>()
        assertTrue(application is MyTestApplication)
        assertTrue((application as MyTestApplication).onCreateWasCalled)
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
        }
    }

    class MyTestApplication : Application() {
        internal var onCreateWasCalled = false

        override fun onCreate() {
            this.onCreateWasCalled = true
        }
    }
}