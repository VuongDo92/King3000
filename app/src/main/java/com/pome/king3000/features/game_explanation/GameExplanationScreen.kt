package com.pome.king3000.features.game_explanation

import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pome.king3000.R
import com.pome.king3000.components.King3000ActionButton
import com.pome.king3000.components.King3000Scaffold
import com.pome.king3000.components.King3000Toolbar
import com.pome.king3000.components.utils.DropDownItem
import com.pome.king3000.features.components.GamePlayInfo
import com.pome.king3000.ui.theme.ArrowLeftIcon
import com.pome.king3000.ui.theme.ExclamationMarkIcon
import com.pome.king3000.ui.theme.King3000Black
import com.pome.king3000.ui.theme.King3000Pinky
import com.pome.king3000.ui.theme.King3000Theme
import com.pome.king3000.ui.theme.King3000White
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameExplanationScreenRoot(
    viewModel: GameExplanationViewModel = koinViewModel(),
    onCloseClick: () -> Unit
) {
    GameExplanationScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                GameExplanationAction.OnCloseClick -> onCloseClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameExplanationScreen(
    state: GameExplanationState,
    onAction: (GameExplanationAction) -> Unit
) {
    King3000Scaffold(
        withGradient = false,
        topAppBar = {
            King3000Toolbar(
                showBackButton = true,
                title = "Game Explanation",
                modifier = Modifier.fillMaxWidth(),
                onBackClick = {
                    onAction(GameExplanationAction.OnCloseClick)
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 16.dp,
                    horizontal = 16.dp
                )) {
                GamePlayInfo()
                Spacer(modifier = Modifier.height(8.dp))
                HtmlViewer(localFileUrl = "file:///android_asset/game_explanation.html")
            }
        }
    }
}

@Composable
fun HtmlViewer(
    localFileUrl: String
) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            loadUrl(localFileUrl)
        }
    })
}

@Preview
@Composable
private fun GameExplanationScreenPreview() {
    King3000Theme {
        GameExplanationScreen(
            state = GameExplanationState(),
            onAction = {}
        )
    }
}