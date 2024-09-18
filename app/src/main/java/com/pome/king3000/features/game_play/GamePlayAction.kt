package com.pome.king3000.features.game_play


sealed interface GamePlayAction {
    data object OnSwipeRightClick : GamePlayAction
    data object OnSwipeLeftClick : GamePlayAction
    data object OnSwipeUpClick : GamePlayAction
    data object OnSwipeDownClick : GamePlayAction
}