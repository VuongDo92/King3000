package com.pome.king3000.features.game_play


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pome.king3000.R
import com.pome.king3000.components.card_swiper.CardSwiperState
import com.pome.king3000.components.card_swiper.rememberCardSwiperState
import com.pome.king3000.data.Devil
import com.pome.king3000.data.SwordStore
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.domain.repository.GamePlayRepository
import com.pome.king3000.ui.UiText
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
        ),
//        suggestedSwordState = SwordStore.reversed().map { it to CardSwiperState() }
    ))
        private set

//    init {
//
//    }

    private val evenChannel = Channel<GamePlayEvent>()
    val events = evenChannel.receiveAsFlow()

    fun onAction(action: GamePlayAction) {
        when (action) {
            is GamePlayAction.OnSwipeRightClick -> {
                state.suggestedSwords.remove(action.selectedSword)
                state = state.copy(
                    swords = state.swords + action.selectedSword,
                    hint = action.message,
                )
            }
            is GamePlayAction.OnSwipeLeftClick -> {
                state.suggestedSwords.remove(action.skippedSword)
                state = state.copy(
                    hint = action.message
                )
            }

            GamePlayAction.OnChooseDone -> {
                onFinishingGame()
            }

            is GamePlayAction.OnSwipeCancel -> {
                state = state.copy(
                    hint = action.message
                )
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