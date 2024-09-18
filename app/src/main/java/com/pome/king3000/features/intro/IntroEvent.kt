package com.pome.king3000.features.intro

import com.pome.king3000.ui.UiText

sealed interface IntroEvent {

    data class Error(val error: UiText) : IntroEvent
}