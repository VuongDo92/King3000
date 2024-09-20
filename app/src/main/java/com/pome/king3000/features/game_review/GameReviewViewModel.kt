package com.pome.king3000.features.game_review


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pome.king3000.domain.repository.GamePlayRepository


class GameReviewViewModel(
    private val gamePlayRepository: GamePlayRepository
) : ViewModel() {

    var state by mutableStateOf(GameReviewState())
        private set

    init {
        val playerName = gamePlayRepository.getPlayerName()
        println("PlayerName: $playerName")

        state = state.copy(
            warriorName = playerName,
        )
    }

    fun onAction(action: GameReviewAction) {
//        when(action) {
//            GameReviewAction.OnQuitClick -> {
//
//            }
//            GameReviewAction.OnReplayClick -> {
//
//            }
//        }
    }
}