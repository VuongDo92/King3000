package com.pome.king3000.features.intro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pome.king3000.R
import com.pome.king3000.components.King3000ActionButton
import com.pome.king3000.components.King3000Scaffold
import com.pome.king3000.features.components.GamePlayInfo
import com.pome.king3000.features.game_play.GamePlayEvent
import com.pome.king3000.features.intro.components.InputWarriorNameDialog
import com.pome.king3000.ui.ObserveAsEvents
import com.pome.king3000.ui.theme.King3000Black
import com.pome.king3000.ui.theme.King3000Theme
import com.pome.king3000.ui.theme.King3000White
import org.koin.androidx.compose.koinViewModel

@Composable
fun IntroScreenRoot(
    viewModel: IntroViewModel = koinViewModel(),
    onNextClick: (String) -> Unit,
    onReadMoreClick: () -> Unit
) {
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is IntroEvent.GoPlaying -> {
                onNextClick(event.warriorName)
            }

            else -> Unit
        }
    }
    IntroScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                IntroAction.OnReadMoreClick -> onReadMoreClick()
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
    King3000Scaffold() { padding ->
        if (state.isInputtingWarriorName) {
            InputWarriorNameDialog(
                onNextClick = { warriorName ->
                    onAction(IntroAction.OnNextClick(warriorName))
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                )) {
                GamePlayInfo()
                Text(
                    text = stringResource(id = R.string.intro_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.intro_description1),
                    style = MaterialTheme.typography.bodySmall,
                    color = King3000White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.intro_description2),
                    style = MaterialTheme.typography.bodySmall,
                    color = King3000White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.intro_description3),
                    style = MaterialTheme.typography.bodySmall,
                    color = King3000White
                )
                Text(
                    text = stringResource(id = R.string.read_more),
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = King3000Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            onAction(IntroAction.OnReadMoreClick)
                        }
                )
            }
            King3000ActionButton(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 32.dp
                    )
                    .align(Alignment.BottomCenter),
                text = stringResource(id = R.string.play),
                isLoading = false
            )
            {
                onAction(IntroAction.OnPlayClick)
            }
        }
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