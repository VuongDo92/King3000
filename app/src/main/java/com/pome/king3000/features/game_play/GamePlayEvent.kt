package com.pome.king3000.features.game_play

import com.pome.king3000.data.GameResult


sealed interface GamePlayEvent {
    data class Over(val gameResult: GameResult) : GamePlayEvent
}