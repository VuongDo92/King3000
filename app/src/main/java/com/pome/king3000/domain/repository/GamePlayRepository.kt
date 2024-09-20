package com.pome.king3000.domain.repository

import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.Sword
import com.pome.king3000.data.Warrior

interface GamePlayRepository {

    /**
     * attachValue -> value 1
     * attachWeight -> weight 1
     * defendValue -> value 2
     * defendWeight -> weight 2
     * capacity --> weight max
     * */
    fun loadSwordValue(
        attackValue: Long,
        attackWeight: Long,
        defendValue: Long,
        defendWeight: Long,
        capacity: Long
    ): Long

    fun finishingGame(
        warrior: Warrior,
        devil: Devil,
        chosenSwords: List<Sword>
    ): GameResult
}