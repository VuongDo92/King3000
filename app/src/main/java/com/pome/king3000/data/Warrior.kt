package com.pome.king3000.data

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Warrior(
    val name: String? = null,
    val characteristic: Long,
    val physicalPower: Long, // this is a capacity of weight
    @DrawableRes val drawableResId: Int,
)
