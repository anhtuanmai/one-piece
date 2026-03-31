package demo.at.ram.presentation.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import demo.at.ram.domain.error.AppError
import demo.at.ram.presentation.RobolectricExtensionSelfTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(org.robolectric.RobolectricTestRunner::class)
@Config(application = RobolectricExtensionSelfTest.MyTestApplication::class)
class ErrorMessageResolverImplTest {

    lateinit var errorMessageResolverImpl : ErrorMessageResolverImpl

    @Before
    fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Context>()
        errorMessageResolverImpl = ErrorMessageResolverImpl(application)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun resolveErrorMessage() {
        val error = AppError.GeneralError.UnexpectedError
        val expected = "General error"

        assertEquals(expected , errorMessageResolverImpl.resolveErrorMessage(error))
    }

}