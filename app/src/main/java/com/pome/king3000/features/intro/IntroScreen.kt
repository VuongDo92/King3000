package com.pome.king3000.features.intro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pome.king3000.ui.theme.King3000Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun IntroScreenRoot(
    viewModel: IntroViewModel = koinViewModel(),
    onPlayClick: () -> Unit
) {
    IntroScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                IntroAction.OnPlayClick -> onPlayClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun IntroScreen(
    state: IntroState,
    onAction: (IntroAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Game Intro", modifier = Modifier
            .align(Alignment.Center)
            .clickable {
                onAction(IntroAction.OnPlayClick)
            }
        )
    }
}

@Preview
@Composable
private fun IntroViewModelScreenPreview() {
    King3000Theme {
        IntroScreen(
            state = IntroState(),
            onAction = {}
        )
    }
}