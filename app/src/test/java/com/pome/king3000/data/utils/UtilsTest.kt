package com.pome.king3000.data.utils

import kotlin.test.Test
import kotlin.test.assertTrue

class UtilsTest {

    @Test
    fun `test random attack value`() {
        // When
        val result = Utils.randomSwordAttackValue()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }

    @Test
    fun `test random attack weight`() {
        // When
        val result = Utils.randomSwordAttackWeight()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }

    @Test
    fun `test random defend value`() {
        // When
        val result = Utils.randomSwordDefendValue()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }

    @Test
    fun `test random defend weight`() {
        // When
        val result = Utils.randomSwordDefendWeight()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }

    @Test
    fun `test random devil power`() {
        // When
        val result = Utils.randomDevilPower()
        // Then
        assertTrue(result in 50L..100L, "Result should be in the range 50 to 100")
    }

    @Test
    fun `test random warrior characteristic`() {
        // When
        val result = Utils.randomWarriorCharacteristicValue()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }

    @Test
    fun `test random warrior physical power`() {
        // When
        val result = Utils.randomWarriorPhysicalPowerValue()
        // Then
        assertTrue(result in 1L..10L, "Result should be in the range 1 to 10")
    }
}