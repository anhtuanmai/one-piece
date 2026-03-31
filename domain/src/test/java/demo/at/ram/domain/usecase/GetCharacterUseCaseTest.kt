package demo.at.ram.domain.usecase

import app.cash.turbine.test
import demo.at.ram.domain.model.Character
import demo.at.ram.domain.repository.OnePieceRepository
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

class GetCharacterUseCaseTest {

    @MockK
    private lateinit var onePieceRepository : OnePieceRepository

    private lateinit var getCharacterUseCase: GetCharacterUseCase

    @Before
    fun setUp() {
        onePieceRepository = mockk(relaxed = true)
        getCharacterUseCase = GetCharacterUseCase(onePieceRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun invoke() = runTest {
        // Given
        val character = Character(id = 1, name = "Monkey D Luffy")
        every { onePieceRepository.getCharacter(1) } returns flowOf(character)

        // When & Then
        getCharacterUseCase(1).test {
            val item = awaitItem()
            assertEquals(character, item)
            awaitComplete()
        }
    }

}