package com.pome.king3000.data

import com.pome.king3000.R
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.domain.repository.GamePlayRepository
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class GameResultTest {

    @Mock
    private lateinit var repository: GamePlayRepository

    private lateinit var devil: Devil
    private lateinit var warrior: Warrior
    private lateinit var chosenSwords: List<Sword>

    private var chosenSwordQuantity: Int = 0
    private var chosenSwordQuality: Long = 0

    @BeforeTest
    fun setUp() {
        devil = Devil(
            power = Utils.randomDevilPower()
        )
        warrior = Warrior(
            name = "King",
            characteristic = Utils.randomWarriorCharacteristicValue(),
            physicalPower = Utils.randomWarriorPhysicalPowerValue(),
            drawableResId = 10
        )
        chosenSwords = listOf(
            Sword(name = "Captain Shield",
                drawableResId = R.drawable.captain_shield,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            ),
            Sword(name = "Iron Man Helmet",
                drawableResId = R.drawable.master_sword,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            ),
            Sword(name = "Sanguineous Crusader Sword",
                drawableResId = R.drawable.jade_diamond_sword,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            ),
            Sword(name = "Blair Diamond Sword",
                drawableResId = R.drawable.blair_diamond_sword,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            )
        )

        chosenSwordQuantity = chosenSwords.size
        chosenSwordQuality = 0
        chosenSwords.forEach { sw ->
            chosenSwordQuality += repository.loadSwordValue(
                attackValue = sw.attackValue,
                attackWeight = sw.attackWeight,
                defendValue = sw.defendValue,
                defendWeight = sw.defendWeight,
                capacity = warrior.physicalPower
            )
        }
    }

    @Test
    fun `test the accuracy of a game result`() {
        // Given
        val title = "Victory"

        // When
        val result = GameResult(
            title = title,
            devil = devil,
            warrior = warrior,
            warriorFinalPower = chosenSwordQuality + warrior.characteristic,
            chosenSword = chosenSwords,
            weaponQuality = chosenSwordQuantity.toLong(),
        )

        // Then
        // VIC: warrior final power > devil power
//        assertTrue {
//            result.title == "Victory" && result.warriorFinalPower > result.devil.power
//        }
    }
}