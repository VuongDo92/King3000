package com.pome.king3000.features.game_play


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pome.king3000.R
import com.pome.king3000.data.Devil
import com.pome.king3000.data.SwordStore
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.domain.repository.GamePlayRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class GamePlayViewModel(
    private val gamePlayRepository: GamePlayRepository
) : ViewModel() {

    var state by mutableStateOf(GamePlayState(
        warrior = Warrior(
            characteristic = Utils.randomWarriorCharacteristicValue(),
            physicalPower = Utils.randomWarriorPhysicalPowerValue(),
            drawableResId = R.drawable.zombie_king
        ),
        suggestedSwords = SwordStore.toMutableList(),
        isPlaying = true,
        gameOver = null,
        devil = Devil(
            power = Utils.randomDevilPower()
        )
    ))
        private set

    init {
        state = state.copy(
            suggestedSwords = SwordStore.toMutableList(),
        )
    }

    private val evenChannel = Channel<GamePlayEvent>()
    val events = evenChannel.receiveAsFlow()

    fun onAction(action: GamePlayAction) {
        when (action) {
            is GamePlayAction.OnSwipeRightClick -> {
                state = state.copy(
                    swords = state.swords + action.selectedSword
                )
            }

            GamePlayAction.OnChooseDone -> {
                onFinishingGame()
            }
            else -> Unit
        }
    }

    private fun onFinishingGame() {
        val gameResult = gamePlayRepository.finishingGame(
            warrior = state.warrior,
            devil = state.devil,
            chosenSwords = state.swords
        )

        viewModelScope.launch {
            evenChannel.send(
                GamePlayEvent.Over(
                    gameResult = gameResult
                )
            )
            state = state.copy(
                gameOver = gameResult.status,
                isPlaying = false,
            )
        }
    }
}