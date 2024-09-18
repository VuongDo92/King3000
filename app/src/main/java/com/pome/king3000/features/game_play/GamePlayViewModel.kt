package com.pome.king3000.features.game_play


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


class GamePlayViewModel() : ViewModel() {

    var state by mutableStateOf(GamePlayState())
        private set

    private val evenChannel = Channel<GamePlayEvent>()
    val events = evenChannel.receiveAsFlow()

    init {

    }

    fun onAction(action: GamePlayAction) {

    }
}