package com.pome.king3000.features.game_play

import com.pome.king3000.components.card_swiper.CardSwiperState
import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameOver
import com.pome.king3000.data.Sword
import com.pome.king3000.data.Warrior


data class GamePlayState(
    val warrior: Warrior,
    val suggestedSwords: MutableList<Sword> = mutableListOf<Sword>(),
    val swords: List<Sword> = emptyList(),
    val devil: Devil,
    val isPlaying: Boolean = false,
    val gameOver: GameOver? = null,
    val hint: String = "Swipe a card or press a button below",
    val suggestedSwordState: List<Pair<Sword, CardSwiperState>> = emptyList()
)