package com.pome.king3000.data

import com.pome.king3000.data.utils.Utils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SwordTest {

    @Test
    fun `test init a sword`() {
        // Given
        val name = "Blair Diamond Sword"

        val attackValue = 2L
        val attackWeight = 3L
        val defendValue = 4L
        val defendWeight = 5L
        val drawableResId = 12

        // When
        val result = Sword(
            name = name,
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            drawableResId = drawableResId
        )

        // Then
        assertEquals("Blair Diamond Sword", result.name)
        assertEquals(2L, result.attackValue)
        assertEquals(3L, result.attackWeight)
        assertEquals(4L, result.defendValue)
        assertEquals(5L, result.defendWeight)
        assertEquals(12, result.drawableResId)
    }

    @Test
    fun `test init a sword with a random value`() {
        // Given
        val name = "Blair Diamond Sword"

        val attackValue = Utils.randomSwordAttackValue()
        val attackWeight = Utils.randomSwordAttackWeight()
        val defendValue = Utils.randomSwordDefendValue()
        val defendWeight = Utils.randomSwordDefendWeight()

        val drawableResId = 12

        // When
        val result = Sword(
            name = name,
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            drawableResId = drawableResId
        )

        // Then
        assertEquals("Blair Diamond Sword", result.name)
        assertTrue(result.attackValue in 0L..10L, "The attack value of a sword must be in the range 0 to 10")
        assertTrue(result.attackWeight in 0L .. 10L, "The attack weight of a sword must be in the range 0 to 10")
        assertTrue(result.defendValue in 0L .. 10L, "The defend value of a sword must be in the range 0 to 10")
        assertTrue(result.defendWeight in 0L .. 10L, "The defend weight of a sword must be in the range 0 to 10")
        assertEquals(12, result.drawableResId)
    }
}