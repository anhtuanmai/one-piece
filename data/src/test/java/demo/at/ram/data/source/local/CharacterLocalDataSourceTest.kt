package demo.at.ram.data.source.local

import androidx.room.Room
import demo.at.ram.data.source.local.dao.CharacterDao
import demo.at.ram.data.source.local.dao.FruitDao
import demo.at.ram.data.source.local.entity.CharacterEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import kotlin.random.Random

@RunWith(org.robolectric.RobolectricTestRunner::class)
class CharacterLocalDataSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var characterDao: CharacterDao
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

        characterDao = database.characterDao()
        fruitDao = database.fruitDao()

        localDS = LocalDataSource(characterDao, fruitDao)
    }

    @Test
    fun saveAndLoadCharacters() = runTest {
        // When 1 : robolectric database is empty at Start
        val mustEmpty = localDS.loadCharacters()

        // Then 1
        assertEquals(0, mustEmpty.size)

        // Given 2
        val random = Random(36).nextLong(99999)
        val expectedCharacters = listOf(
            CharacterEntity(id = random, name = "Monkey D Luffy"),
            CharacterEntity(id = random + 1, name = "Roronoa Zoro"),
        )

        // When 2
        localDS.saveCharacters(expectedCharacters)
        val characters = localDS.loadCharacters()

        // Then 2
        assertEquals(expectedCharacters, characters)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

}