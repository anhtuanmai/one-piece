package demo.at.ram.data.source.local

import androidx.room.Room
import demo.at.ram.data.source.local.dao.FruitDao
import demo.at.ram.data.source.local.entity.FruitEntity
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.RuntimeEnvironment
import tech.apter.junit.jupiter.robolectric.RobolectricExtension
import kotlin.random.Random

@ExtendWith(RobolectricExtension::class)
class FruitLocalDataSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var fruitDao: FruitDao
    private lateinit var localDS: LocalDataSource

    @BeforeEach
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

    @AfterEach
    fun closeDatabase() {
        database.close()
    }
}
