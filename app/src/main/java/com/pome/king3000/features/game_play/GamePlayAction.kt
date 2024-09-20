package com.pome.king3000.features.game_play

import com.pome.king3000.data.Sword


sealed interface GamePlayAction {
    data class OnSwipeRightClick(val selectedSword: Sword) : GamePlayAction
    data object OnChooseDone : GamePlayAction

    data object OnSwipeLeftClick : GamePlayAction
    data object OnSwipeUpClick : GamePlayAction
    data object OnSwipeDownClick : GamePlayAction
}