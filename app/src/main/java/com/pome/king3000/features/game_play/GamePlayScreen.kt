package com.pome.king3000.features.game_play

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
fun GamePlayScreenRoot(
    viewModel: GamePlayViewModel = koinViewModel()
) {
    GamePlayScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun GamePlayScreen(
    state: GamePlayState,
    onAction: (GamePlayAction) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Game Play", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
private fun GamePlayScreenPreview() {
    King3000Theme {
        GamePlayScreen(
            state = GamePlayState(),
            onAction = {}
        )
    }
}