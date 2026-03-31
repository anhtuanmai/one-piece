package demo.at.ram.shared

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.Assert.assertEquals


class CheckInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("demo.at.ram.shared.test", appContext.packageName)
    }
}
