package com.pome.king3000.data

import com.pome.king3000.data.utils.Utils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DevilTest {

    @Test
    fun `test init a devil`() {
        // Given
        val power = 52L

        // When
        val result = Devil(
            power = power
        )

        // When
        assertEquals(52L, result.power)
    }

    @Test
    fun `test init a devil with a random power value`() {
        // Given
        val power = Utils.randomDevilPower()

        // When
        val result = Devil(
            power = power
        )

        // When
        assertTrue(result.power in 50L..100L, "The power of devil should be in the range 50 to 100")
    }
}