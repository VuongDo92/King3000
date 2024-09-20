package com.pome.king3000.data.utils


object Utils {

    private const val ATTACK_VALUE_MIN = 1L
    private const val ATTACK_VALUE_MAX = 10L

    private const val ATTACK_WEIGHT_MIN = 1L
    private const val ATTACK_WEIGHT_MAX = 10L

    private const val DEFEND_VALUE_MIN = 1L
    private const val DEFEND_VALUE_MAX = 10L

    private const val DEFEND_WEIGHT_MIN = 1L
    private const val DEFEND_WEIGHT_MAX = 10L

    private const val DEVIL_POWER_MIN = 50L
    private const val DEVIL_POWER_MAX = 100L

    private const val WARRIOR_CHARACTERISTIC_MIN = 1L
    private const val WARRIOR_CHARACTERISTIC_MAX = 10L

    private const val WARRIOR_PHYSICAL_POWER_MIN = 1L
    private const val WARRIOR_PHYSICAL_POWER_MAX = 10L

    fun randomSwordAttackValue(): Long {
        return (ATTACK_VALUE_MIN..ATTACK_VALUE_MAX).random()
    }

    fun randomSwordAttackWeight(): Long {
        return (ATTACK_WEIGHT_MIN..ATTACK_WEIGHT_MAX).random()
    }

    fun randomSwordDefendValue(): Long {
        return (DEFEND_VALUE_MIN..DEFEND_VALUE_MAX).random()
    }

    fun randomSwordDefendWeight(): Long {
        return (DEFEND_WEIGHT_MIN..DEFEND_WEIGHT_MAX).random()
    }

    fun randomDevilPower(): Long {
        return (DEVIL_POWER_MIN..DEVIL_POWER_MAX).random()
    }

    fun randomWarriorCharacteristicValue(): Long {
        return (WARRIOR_CHARACTERISTIC_MIN..WARRIOR_CHARACTERISTIC_MAX).random()
    }

    fun randomWarriorPhysicalPowerValue(): Long {
        return (WARRIOR_PHYSICAL_POWER_MIN..WARRIOR_PHYSICAL_POWER_MAX).random()
    }
}
