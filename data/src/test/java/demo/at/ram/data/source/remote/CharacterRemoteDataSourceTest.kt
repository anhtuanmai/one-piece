package demo.at.ram.data.source.remote

import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharacterRemoteDataSourceTest {

    @MockK
    lateinit var ramService: RamService

    private lateinit var characterRemoteDataSource: CharacterRemoteDataSource

    @Before
    fun setUp() {
        ramService = mockk(relaxed = true)
        characterRemoteDataSource = CharacterRemoteDataSource(ramService)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getAllCharacters() = runTest {
        // Then
        characterRemoteDataSource.getAllCharacters()

        // Then
        coVerify(exactly = 1) { ramService.getAllCharacters() }
    }
}