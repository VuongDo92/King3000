package com.pome.king3000.features.game_review

sealed interface GameReviewAction {
    data object OnQuitClick : GameReviewAction
    data class OnReplayClick(val playerName: String) : GameReviewAction
}