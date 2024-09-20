package com.pome.king3000.data

import com.pome.king3000.data.utils.Utils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WarriorTest {

    @Test
    fun `test init a warrior`() {
        // Given
        val name = "King"
        val characteristic = Utils.randomWarriorCharacteristicValue()
        val physicalPower = Utils.randomWarriorPhysicalPowerValue()
        val drawableResId = 10

        // When
        val result = Warrior(
            name = name,
            characteristic = characteristic,
            physicalPower = physicalPower,
            drawableResId = drawableResId
        )

        // Then
        assertEquals("King", result.name)
        assertEquals(10, result.drawableResId)
        assertTrue(result.characteristic in 1L..10L, "The characteristic of a warrior must be in the rang 1 to 10")
        assertTrue(result.physicalPower in 1L..10L, "The physical power of a warrior must be in the rang 1 to 10")
    }
}