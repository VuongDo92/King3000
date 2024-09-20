package com.pome.king3000.features.game_review

import com.pome.king3000.ui.UiText

sealed interface GameReviewEvent {

    data class Error(val error: UiText) : GameReviewEvent
}