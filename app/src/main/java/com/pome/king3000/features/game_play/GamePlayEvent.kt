package com.pome.king3000.features.game_play


sealed interface GamePlayEvent {

    data object Win : GamePlayEvent
    data object Loss : GamePlayEvent
}