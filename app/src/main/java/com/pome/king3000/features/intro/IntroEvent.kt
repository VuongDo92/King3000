package com.pome.king3000.features.intro

sealed interface IntroEvent {

    data class GoPlaying(val warriorName: String) : IntroEvent
}