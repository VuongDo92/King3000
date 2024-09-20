package com.pome.king3000.features.game_play

import com.pome.king3000.data.Sword


sealed interface GamePlayAction {
    data object OnChooseDone : GamePlayAction

    data class OnSwipeCancel(val message: String) : GamePlayAction

    data class OnSwipeRightClick(val selectedSword: Sword, val message: String) : GamePlayAction
    data class OnSwipeLeftClick(val skippedSword: Sword, val message: String) : GamePlayAction
}