package com.pome.king3000.data.repository

import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameOver
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.Sword
import com.pome.king3000.data.Warrior
import com.pome.king3000.domain.repository.GamePlayRepository
import java.lang.Long.max

class GamePlayRepositoryImpl() : GamePlayRepository {

    override fun loadSwordValue(
        attackValue: Long,
        attackWeight: Long,
        defendValue: Long,
        defendWeight: Long,
        capacity: Long
    ): Long {
        // Check if we can take both items
        if (attackWeight + defendWeight <= capacity) {
            return attackValue + defendValue
        }
        // Check if we can only take the attack item
        if (capacity in attackWeight..<defendWeight) {
            return attackValue
        }
        // Check if we can only take the defend item
        if (capacity in defendWeight..<attackWeight) {
            return defendValue
        }
        // If both items' weights are individually within the capacity, return the one with the highest value
        if (attackWeight <= capacity && defendWeight <= capacity) {
            return max(attackValue, defendValue)
        }
        return 0L
    }

    override fun finishingGame(
        warrior: Warrior,
        devil: Devil,
        chosenSwords: List<Sword>
    ): GameResult {
        val capacity = warrior.physicalPower
        var chosenSwordQuality = 0L
        chosenSwords.forEach { sw ->
            val attackValue = sw.attackValue
            val attackWeight = sw.attackWeight
            val defendValue = sw.defendValue
            val defendWeight = sw.defendWeight

            chosenSwordQuality += loadSwordValue(
                attackValue = attackValue,
                attackWeight = attackWeight,
                defendValue = defendValue,
                defendWeight = defendWeight,
                capacity = capacity
            )
        }
        val finalWarriorPower = chosenSwordQuality + warrior.characteristic
        val pairTitles = if (finalWarriorPower > devil.power) {
            GameOver.VICTORY to "Victory"

        } else if (finalWarriorPower < devil.power) {
            GameOver.DEFEATED to "Defeated"
        } else {
            GameOver.DRAW to "Draw"
        }

        return GameResult(
            title = pairTitles.second,
            status = pairTitles.first,
            devil = devil,
            warrior = warrior,
            warriorFinalPower = finalWarriorPower,
            chosenSword = chosenSwords,
            weaponQuality = chosenSwordQuality
        )
    }
}