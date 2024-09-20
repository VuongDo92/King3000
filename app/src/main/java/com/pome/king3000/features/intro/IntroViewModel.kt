package com.pome.king3000.features.intro


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pome.king3000.domain.repository.GamePlayRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class IntroViewModel(
    private val gamePlayRepository: GamePlayRepository
) : ViewModel() {

    var state by mutableStateOf(IntroState())
        private set

    private val evenChannel = Channel<IntroEvent>()
    val events = evenChannel.receiveAsFlow()

    fun onAction(action: IntroAction) {

        when (action) {
            IntroAction.OnPlayClick -> {
                state = state.copy(isInputtingWarriorName = true)
            }

            is IntroAction.OnNextClick -> {
                state = state.copy(
                    isInputtingWarriorName = false
                )
                gamePlayRepository.savePlayerName(action.warriorName)
                viewModelScope.launch {
                    evenChannel.send(IntroEvent.GoPlaying(action.warriorName))
                }
            }

            else -> Unit
        }
    }
}