package com.pome.king3000.data

import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    val title: String? = null,
    val status: GameOver? = null,
    val devil: Devil,
    val warrior: Warrior,
    val warriorFinalPower: Long,
    val chosenSword: List<Sword>,
    val weaponQuality: Long
)


@Serializable
enum class GameOver {
    VICTORY,
    DEFEATED,
    DRAW,
}
