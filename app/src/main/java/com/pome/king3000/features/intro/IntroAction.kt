package com.pome.king3000.features.intro

sealed interface IntroAction {
    data object OnPlayClick : IntroAction
}