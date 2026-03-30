package demo.at.ram.domain.usecase

import app.cash.turbine.test
import demo.at.ram.domain.model.Character
import demo.at.ram.domain.repository.CompositeCharacterRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetFavoritesUseCaseTest {

    @MockK
    private lateinit var repository : CompositeCharacterRepository

    private lateinit var useCase: GetFavoritesUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = GetFavoritesUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun invoke() = runTest {
        // Given
        val characters = listOf(
            Character(id = 1, name = "Monkey D Luffy"),
            Character(id = 2, name = "Roronoa Zoro")
        )
        every { repository.getFavorites() } returns flowOf(characters)

        // When & Then
        useCase().test {
            val item = awaitItem()
            assertEquals(characters, item)
            awaitComplete()
        }
    }

}