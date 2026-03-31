package demo.at.ram.data.source.local

import android.app.Application
import androidx.room.Room
import demo.at.ram.data.source.local.dao.FruitDao
import demo.at.ram.data.source.local.entity.FruitEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.random.Random

@RunWith(org.robolectric.RobolectricTestRunner::class)
@Config(application = FruitLocalDataSourceTest.MyTestApplication::class)
class FruitLocalDataSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var fruitDao: FruitDao
    private lateinit var localDS: LocalDataSource

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.getApplication(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        fruitDao = database.fruitDao()
        localDS = LocalDataSource(database.characterDao(), fruitDao)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun saveAndLoadFruits() = runTest {
        // When 1 : database is empty at start
        val mustEmpty = localDS.loadFruits()

        // Then 1
        assertEquals(0, mustEmpty.size)

        // Given 2
        val random = Random(42).nextLong(99999)
        val expectedFruits = listOf(
            FruitEntity(id = random, name = "Gum-Gum Fruit"),
            FruitEntity(id = random + 1, name = "Flame-Flame Fruit"),
        )

        // When 2
        localDS.saveFruits(expectedFruits)
        val fruits = localDS.loadFruits()

        // Then 2
        assertEquals(expectedFruits, fruits)
    }

    class MyTestApplication : Application() {
        internal var onCreateWasCalled = false

        override fun onCreate() {
            this.onCreateWasCalled = true
        }
    }

}
