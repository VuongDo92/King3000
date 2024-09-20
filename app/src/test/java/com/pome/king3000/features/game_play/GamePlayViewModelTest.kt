package com.pome.king3000.features.game_play

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameOver
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.Sword
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.domain.repository.GamePlayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class GamePlayViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // Ensures LiveData updates happen immediately

    @Mock
    private lateinit var gamePlayRepository: GamePlayRepository

    private lateinit var gamePlayViewModel: GamePlayViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val warrior = Warrior(
        name = "King",
        characteristic = 1L,
        physicalPower = 1L,
        drawableResId = 12
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        gamePlayViewModel = GamePlayViewModel(gamePlayRepository)
        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset dispatcher after tests
    }

    @Test
    fun `onSwipeRightClick adds selected sword to the warrior's swords`() {
        // Given
        val initialSword = Sword(
            name = "Diamond Sword",
            attackValue = 10,
            attackWeight = 5,
            defendValue = 1,
            defendWeight = 4L,
            drawableResId = 10
        )
        gamePlayViewModel.state = gamePlayViewModel.state.copy(swords = mutableListOf())

        // When
        gamePlayViewModel.onAction(GamePlayAction.OnSwipeRightClick(selectedSword = initialSword))

        // Then
        assertEquals(1, gamePlayViewModel.state.swords.size)
        assertEquals(initialSword, gamePlayViewModel.state.swords[0])
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onChooseDone triggers game finishing logic`() = runTest {
        // Given
        val sword1 = Sword(
            name = "Blair Diamond Sword",
            attackValue = 2,
            attackWeight = 8,
            defendValue = 5,
            defendWeight = 8,
            drawableResId = 10)

        val sword2 = Sword(
            name = "Blair Diamond Sword",
            attackValue = 3,
            attackWeight = 6,
            defendValue = 3,
            defendWeight = 7,
            drawableResId = 11)

        gamePlayViewModel.state = gamePlayViewModel.state.copy(
            swords = listOf(sword1, sword2),
            warrior = Warrior(
                characteristic = 2,
                physicalPower = 9,
                drawableResId = 12
            ),
            devil = Devil(
                power = 50L
            )
        )

        // Mocking the repository
        val expectedGameResult = GameResult(
            title = "Defeated",
            status = GameOver.DEFEATED,
            devil = gamePlayViewModel.state.devil,
            warrior = gamePlayViewModel.state.warrior,
            warriorFinalPower = 20L,
            chosenSword = gamePlayViewModel.state.swords,
            weaponQuality = 5L
        )
        `when`(gamePlayRepository.finishingGame(
            warrior = warrior,
            devil = Devil(
                power = 50L
            ),
            chosenSwords = listOf(sword1, sword2)
        )).thenReturn(expectedGameResult)

        // Variable to hold the event
        var receivedEvent: GamePlayEvent? = null

        // Launch a coroutine to collect from the events Flow
        val job = launch {
            gamePlayViewModel.events.take(1).collect { event ->
                receivedEvent = event
            }
        }

        // When
        gamePlayViewModel.onAction(GamePlayAction.OnChooseDone)

        // Then
        advanceUntilIdle() // Wait for coroutines to complete

        // Ensure event is not null and is of the correct type
        assertNotNull(receivedEvent, "Expected an event, but none was received.")
        assertTrue(receivedEvent is GamePlayEvent.Over, "Expected a GamePlayEvent.Over event.")

        receivedEvent as GamePlayEvent.Over
        assertEquals(GameOver.DEFEATED, (receivedEvent as GamePlayEvent.Over).gameResult.status)
        assertEquals(false, gamePlayViewModel.state.isPlaying)

        // Cleanup
        job.cancel() // Cancel the collecting job
    }
}