package com.pome.king3000.features.game_review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pome.king3000.R
import com.pome.king3000.components.King3000ActionButton
import com.pome.king3000.components.King3000Scaffold
import com.pome.king3000.components.King3000Toolbar
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.Sword
import com.pome.king3000.data.SwordStore
import com.pome.king3000.features.components.GamePlayInfo
import com.pome.king3000.features.game_review.components.SwordReviewCard
import com.pome.king3000.ui.theme.King3000DarkRed
import com.pome.king3000.ui.theme.King3000Lavender
import com.pome.king3000.ui.theme.King3000Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameReviewScreenRoot(
    viewModel: GameReviewViewModel = koinViewModel(),
    gameResult: GameResult,
    onQuitClick: () -> Unit,
    onReplayClick: (String) -> Unit
) {
    val st = viewModel.state.copy(
        gameResult = gameResult
    )
    GameReviewScreen(
        state = st,
        onAction = { action ->
            when (action) {
                GameReviewAction.OnQuitClick -> onQuitClick()
                is GameReviewAction.OnReplayClick -> {
                    onReplayClick(action.playerName)
                }
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameReviewScreen(
    state: GameReviewState,
    onAction: (GameReviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)

    King3000Scaffold(
        withGradient = false,
        topAppBar = {
            King3000Toolbar(
                showBackButton = false,
                title = state.gameResult?.title?.uppercase() ?: stringResource(id = R.string.result),
                scrollBehavior = scrollBehavior,
                startContent = {
                    Row {
                        Image(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(24.dp)
                                .aspectRatio(1f),
                            painter = painterResource(R.drawable.swords),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(King3000DarkRed)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(24.dp)
                                .aspectRatio(1f),
                            painter = painterResource(R.drawable.shield),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(King3000Lavender)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 212.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.gameResult!!.chosenSword.toList()) { sw ->
                    SwordReviewCard(
                        modifier = Modifier.fillMaxWidth(),
                        sword = sw)
                }
            }
            // Sticky box at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // Aligns the box to the bottom
                    .padding(16.dp)
            ) {
                Column {
                    GamePlayInfo(
                        coilWidth = 100,
                        coilHeight = 100,
                        gifHeight = 50.dp,
                        gameResult = state.gameResult,
                        playerName = state.warriorName
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    )
                    {
                        King3000ActionButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = stringResource(id = R.string.quit),
                            containerColor = King3000Lavender,
                            isLoading = false
                        ) {
                            onAction(GameReviewAction.OnQuitClick)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        King3000ActionButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = stringResource(id = R.string.replay),
                            isLoading = false
                        ) {
                            onAction(GameReviewAction.OnReplayClick(state.warriorName ?: ""))
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun IntroViewModelScreenPreview() {
    King3000Theme {
        GameReviewScreen(
            state = GameReviewState(),
            onAction = {}
        )
    }
}