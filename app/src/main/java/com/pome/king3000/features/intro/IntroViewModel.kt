package com.pome.king3000.features.intro


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


class IntroViewModel() : ViewModel() {

    var state by mutableStateOf(IntroState())
        private set

    private val evenChannel = Channel<IntroEvent>()
    val events = evenChannel.receiveAsFlow()

    init {

    }

    fun onAction(action: IntroAction) {

    }
}