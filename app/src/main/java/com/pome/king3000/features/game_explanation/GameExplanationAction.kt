package com.pome.king3000.features.game_explanation

sealed interface GameExplanationAction {
    data object OnCloseClick : GameExplanationAction
}