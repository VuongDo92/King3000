package com.pome.king3000.features.game_explanation


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class GameExplanationViewModel() : ViewModel() {

    var state by mutableStateOf(GameExplanationState())
        private set

    fun onAction(action: GameExplanationAction) {

    }
}