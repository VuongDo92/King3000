package com.pome.king3000.features.game_review


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class GameReviewViewModel() : ViewModel() {

    var state by mutableStateOf(GameReviewState())
        private set

    fun onAction(action: GameReviewAction) {
        when(action) {
            GameReviewAction.OnQuitClick -> {

            }
            GameReviewAction.OnReplayClick -> {

            }
            else -> Unit
        }
    }
}