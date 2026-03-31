package demo.at.ram.data.repository

import demo.at.ram.data.source.local.LocalDataSource
import demo.at.ram.data.source.local.entity.CharacterEntity
import demo.at.ram.data.source.remote.CharacterRemoteDataSource
import demo.at.ram.data.source.remote.model.ResponseWrapper
import demo.at.ram.domain.model.Character
import demo.at.ram.shared.model.SourceOrigin
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class CharacterRepositoryImplTest {

    @MockK
    private lateinit var remoteDataSource: CharacterRemoteDataSource

    @MockK
    private lateinit var localDataSource: LocalDataSource

    private lateinit var testScope: CoroutineScope
    private lateinit var testScheduler: TestCoroutineScheduler
    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var repository: OnePieceRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mockk<CharacterRemoteDataSource>(relaxed = true)
        localDataSource = mockk<LocalDataSource>(relaxed = true)

        testScheduler = TestCoroutineScheduler()
        testDispatcher = StandardTestDispatcher(testScheduler)
        testScope = CoroutineScope(SupervisorJob() + testScheduler)
        repository = OnePieceRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            applicationScope = testScope,
            ioDispatcher = testDispatcher,
        )
    }

    @Test
    fun getAllCharacters_testSuccessRemote() = runTest(testScheduler) {
        // Given : Remote OK
        val expectedCharacters = listOf(
            Character(id = 1, name = "Monkey D Luffy"),
            Character(id = 2, name = "Roronoa Zoro")
        )
        expectedCharacters.map { CharacterEntity(it) }
        expectedCharacters.map { it.id }
        val mockResult = Response.success(expectedCharacters)
        coEvery { remoteDataSource.getAllCharacters() } returns ResponseWrapper.wrapHttpResponse(
            mockResult
        )

        // When
        val response = repository.getAllCharacters().first()

        // Then
        assertEquals(true, response.isSuccessful)
        assertEquals(SourceOrigin.REMOTE, response.sourceOrigin)
        assertEquals(expectedCharacters, response.data)
        coVerify(exactly = 1) { remoteDataSource.getAllCharacters() }
        coVerify(exactly = 0) { localDataSource.loadCharacters() }
        coVerify(exactly = 1) { localDataSource.saveCharacters(any()) }
    }

    @Test
    fun getAllCharacters_testSuccessLocal() = runTest {
        // Given : Remote 404 + not empty db
        coEvery { remoteDataSource.getAllCharacters() } returns
                ResponseWrapper.wrapError(IOException("No internet connection"))

        val expectedCharacters = listOf(
            Character(id = 1, name = "Monkey D Luffy"),
            Character(id = 2, name = "Roronoa Zoro")
        )
        coEvery { localDataSource.loadCharacters() } returns expectedCharacters.map {
            CharacterEntity(
                it
            )
        }

        //When
        val response = repository.getAllCharacters().first()
        val characters = response.data

        // Then
        assertEquals(true, response.isSuccessful)
        assertEquals(SourceOrigin.LOCAL, response.sourceOrigin)
        assertEquals(expectedCharacters, characters)
        coVerify(exactly = 1) { remoteDataSource.getAllCharacters() }
        coVerify(exactly = 1) { localDataSource.loadCharacters() }
        coVerify(exactly = 0) { localDataSource.saveCharacters(any()) }
    }

    @Test
    fun getAllCharacters_testFailure() = runTest {
        // Given : Remote 404 + empty db
        coEvery { remoteDataSource.getAllCharacters() } returns
                ResponseWrapper.wrapError(IOException("No internet connection"))
        coEvery { localDataSource.loadCharacters() } returns emptyList()

        //When
        val response = repository.getAllCharacters().first()

        //Then
        assertEquals(false, response.isSuccessful)
        coVerify(exactly = 1) { remoteDataSource.getAllCharacters() }
        coVerify(exactly = 1) { localDataSource.loadCharacters() }
        coVerify(exactly = 0) { localDataSource.saveCharacters(any()) }
    }

    @Test
    fun getSavedCharacters() = runTest {
        // Given
        val expectedCharacters = listOf(
            Character(id = 1, name = "Monkey D Luffy"),
            Character(id = 2, name = "Roronoa Zoro")
        )
        coEvery { localDataSource.loadCharacters() } returns expectedCharacters.map {
            CharacterEntity(
                it
            )
        }

        //When
        val characters = repository.getSavedCharacters().first()

        //Then
        assertEquals(expectedCharacters, characters)
        coVerify(exactly = 1) { localDataSource.loadCharacters() }
    }

}