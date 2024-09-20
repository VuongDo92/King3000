package com.pome.king3000.data.repositoty

import com.pome.king3000.data.Devil
import com.pome.king3000.data.Sword
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.repository.GamePlayRepositoryImpl
import com.pome.king3000.domain.repository.GamePlayRepository
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class GamePlayRepositoryTest {

    private lateinit var gamePlayRepository: GamePlayRepository

    private lateinit var warrior: Warrior
    private lateinit var devil: Devil
    private lateinit var sword1: Sword
    private lateinit var sword2: Sword
    private lateinit var sword3: Sword
    private lateinit var sword4: Sword
    private lateinit var sword5: Sword
    private lateinit var sword6: Sword

    @Before
    fun setUp() {
        gamePlayRepository = GamePlayRepositoryImpl()
        // Sample warrior and devil setup
        warrior = Warrior(name = "King", characteristic = 8, physicalPower = 9, drawableResId = 9999)
        devil = Devil(power = 50)

        // Sample swords
        sword1 = Sword(
            name = "Sword 1",
            attackValue = 3,
            attackWeight = 5,
            defendValue = 4,
            defendWeight = 2,
            drawableResId = 1
        )
        sword2 = Sword(
            name = "Sword 2",
            attackValue = 6,
            attackWeight = 5,
            defendValue = 7,
            defendWeight = 8,
            drawableResId = 2
        )
        sword3 = Sword(
            name = "Sword 3",
            attackValue = 6,
            attackWeight = 3,
            defendValue = 4,
            defendWeight = 2,
            drawableResId = 3
        )
        sword4 = Sword(
            name = "Sword 4",
            attackValue = 8,
            attackWeight = 5,
            defendValue = 3,
            defendWeight = 7,
            drawableResId = 4
        )
        sword5 = Sword(
            name = "Sword 5",
            attackValue = 4,
            attackWeight = 5,
            defendValue = 6,
            defendWeight = 2,
            drawableResId = 5
        )
        sword6 = Sword(
            name = "Sword 6",
            attackValue = 9,
            attackWeight = 7,
            defendValue = 5,
            defendWeight = 4,
            drawableResId = 6
        )
    }

    @Test
    fun `test load attack swords`() {
        // Given
        val capacity = 4L

        val attackValue = 2L
        val attackWeight = 3L
        val defendValue = 4L
        val defendWeight = 5L

        // When
        val result = gamePlayRepository.loadSwordValue(
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            capacity = capacity
        )

        // Then
        val expect = 2L
        assertEquals(expect, result)
    }

    @Test
    fun `test load defend swords`() {
        // Given
        val capacity = 4L

        val attackValue = 2L
        val attackWeight = 5L
        val defendValue = 4L
        val defendWeight = 3L

        // When
        val result = gamePlayRepository.loadSwordValue(
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            capacity = capacity
        )

        // Then
        val expect = 4L
        assertEquals(expect, result)
    }

    @Test
    fun `test load either of swords`() {
        // Given
        val capacity = 6L

        val attackValue = 2L
        val attackWeight = 5L
        val defendValue = 4L
        val defendWeight = 3L

        // When
        val result = gamePlayRepository.loadSwordValue(
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            capacity = capacity
        )

        // Then
        val expect = 4L
        assertEquals(expect, result)
    }

    @Test
    fun `test load both swords`() {
        // Given
        val capacity = 10L

        val attackValue = 2L
        val attackWeight = 5L
        val defendValue = 4L
        val defendWeight = 3L

        // When
        val result = gamePlayRepository.loadSwordValue(
            attackValue = attackValue,
            attackWeight = attackWeight,
            defendValue = defendValue,
            defendWeight = defendWeight,
            capacity = capacity
        )

        // Then
        val expect = 6L
        assertEquals(expect, result)
    }

    @Test
    fun `finishingGame returns Victory when warrior's power exceeds devil's power`() {
        // Given
        warrior = Warrior(name = "King",
            characteristic = 8,
            physicalPower = 9,
            drawableResId = 9999)

        devil = Devil(power = 50)

        val chosenSwords = listOf(
            sword1, // 3 + 4
            sword2, // defend: 7
            sword3, // 6 + 4
            sword4, // attack 8
            sword5, // 4 + 6
            sword6 // 9
        )
        // -> 51 (loaded power) + 8 (power of himself) = 57
        // devil power = 50
        // Act
        val result = gamePlayRepository.finishingGame(warrior, devil, chosenSwords)

        // Assert
        assertEquals("Victory", result.title)
        assertEquals(warrior, result.warrior)
        assertEquals(devil, result.devil)
        assertEquals(59, result.warriorFinalPower)
    }

    @Test
    fun `finishingGame returns Defeated when warrior's power exceeds devil's power`() {
        // Given
        warrior = Warrior(name = "King",
            characteristic = 8,
            physicalPower = 9,
            drawableResId = 9999)

        devil = Devil(power = 50)

        val chosenSwords = listOf(
            sword1, // 3 + 4
            sword2, // defend: 7
            sword3, // 6 + 4
            sword4, // attack 8
        )
        // -> 32 (loaded power) + 8 (power of himself) = 40
        // devil power = 50

        // Act
        val result = gamePlayRepository.finishingGame(warrior, devil, chosenSwords)

        // Assert
        assertEquals("Defeated", result.title)
        assertEquals(warrior, result.warrior)
        assertEquals(devil, result.devil)
        assertEquals(40, result.warriorFinalPower)
    }


    @Test
    fun `finishingGame returns Draw when warrior's power exceeds devil's power`() {
        // Given
        warrior = Warrior(name = "King",
            characteristic = 8,
            physicalPower = 9,
            drawableResId = 9999)

        devil = Devil(power = 50)

        val chosenSwords = listOf(
            sword1, // 3 + 4
            sword2, // defend: 7
            sword3, // 6 + 4
            sword4, // attack 8
            sword5, // 4 + 6
        )
        // -> 32 (loaded power) + 8 (power of himself) = 40
        // devil power = 50

        // Act
        val result = gamePlayRepository.finishingGame(warrior, devil, chosenSwords)

        // Assert
        assertEquals("Draw", result.title)
        assertEquals(warrior, result.warrior)
        assertEquals(devil, result.devil)
        assertEquals(50, result.warriorFinalPower)
    }
}