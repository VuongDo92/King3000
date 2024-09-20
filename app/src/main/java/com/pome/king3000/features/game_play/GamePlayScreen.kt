package com.pome.king3000.features.game_play

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pome.king3000.R
import com.pome.king3000.components.King3000CircleButton
import com.pome.king3000.components.King3000Scaffold
import com.pome.king3000.components.card_swiper.Direction
import com.pome.king3000.components.card_swiper.cardSwiper
import com.pome.king3000.components.card_swiper.rememberCardSwiperState
import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameOver
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.features.components.GamePlayInfo
import com.pome.king3000.features.game_play.components.GameOverDialog
import com.pome.king3000.features.game_play.components.Hint
import com.pome.king3000.features.game_play.components.SwordProfileCard
import com.pome.king3000.ui.ObserveAsEvents
import com.pome.king3000.ui.UiText
import com.pome.king3000.ui.theme.King3000Theme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun GamePlayScreenRoot(
    viewModel: GamePlayViewModel = koinViewModel(),
    warriorName: String,
    onQuitClick: () -> Unit,
    onReviewClick: (GameResult) -> Unit,
) {
    val collectedEvent = remember { mutableStateOf<GamePlayEvent.Over?>(null) }
    collectedEvent.value?.let { over ->
        when(over.gameResult.status) {
            GameOver.DRAW -> {
                GameOverDialog(
                    title = stringResource(id = R.string.drawn).uppercase(),
                    description = stringResource(id = R.string.you_re_drawn),
                    drawableGifResId = R.drawable.neutral,
                    onCancelClick = onQuitClick,
                    onReviewClick = {
                        onReviewClick(
                            over.gameResult
                        )
                    }
                )
            }
            GameOver.DEFEATED -> {
                GameOverDialog(
                    title = stringResource(id = R.string.defeated).uppercase(),
                    description = stringResource(id = R.string.you_re_defeated),
                    drawableGifResId = R.drawable.sad,
                    onCancelClick = onQuitClick,
                    onReviewClick = {
                        onReviewClick(
                            over.gameResult
                        )
                    }
                )
            }
            GameOver.VICTORY -> {
                GameOverDialog(
                    title = stringResource(id = R.string.vic).uppercase(),
                    description = stringResource(id = R.string.you_re_a_victory),
                    drawableGifResId = R.drawable.happy,
                    onCancelClick = onQuitClick,
                    onReviewClick = {
                        onReviewClick(
                            over.gameResult
                        )
                    }
                )
            }

            null -> Unit
        }
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event) {
            is GamePlayEvent.Over -> {
                collectedEvent.value = event
            }
            else -> Unit
        }
    }
    Log.i("Game Play Screen", "WarriorName = $warriorName")
    val stateInitedWarrior = viewModel.state.warrior
    val w = stateInitedWarrior.copy(
        name = warriorName
    )
    GamePlayScreen(
        gamePlayState = viewModel.state.copy(
            warrior = w
        ),
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun GamePlayScreen(
    gamePlayState: GamePlayState,
    onAction: (GamePlayAction) -> Unit
) {
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    King3000Scaffold {
        Box {
            val suggestedSwords = gamePlayState.suggestedSwords.reversed()
                .map { it to rememberCardSwiperState() }

            GamePlayInfo(
                modifier = Modifier.padding(
                    top = 44.dp
                )
            )
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .align(Alignment.Center))
            {
                suggestedSwords.forEach { (sword, state) ->
                    Log.i("Warrior Name", "${gamePlayState.warrior.name}")
                    if (state.swipedDirection == null) {
                        SwordProfileCard(
                            modifier = Modifier
                                .fillMaxSize()
                                .cardSwiper(
                                    state = state,
                                    blockedDirections = listOf(Direction.Down, Direction.Up),
                                    onSwiped = {
                                        // swipes are handled by the LaunchedEffect
                                        // so that we track button clicks & swipes
                                        // from the same place
                                    },
                                    onSwipeCancel = {
                                        Log.d("Swipeable-Card", "Cancelled swipe")
                                        onAction(
                                            GamePlayAction.OnSwipeCancel(
                                                message = UiText
                                                    .StringResource(id = R.string.you_canceled_the_treasure_chest)
                                                    .asString(context = context)
                                            )
                                        )
                                    }
                                ),
                            sword = sword
                        )
                    }
                    LaunchedEffect(sword, state.swipedDirection) {
                        if (state.swipedDirection != null) {
                            if(state.swipedDirection == Direction.Left) {
                                val message = UiText.StringResource(
                                    id = R.string.you_have_skipped_s,
                                    args = arrayOf(sword.name)
                                ).asString(context)
                                onAction(
                                    GamePlayAction.OnSwipeLeftClick(
                                        skippedSword = sword,
                                        message = message
                                    )
                                )
                            } else if(state.swipedDirection == Direction.Right) {
                                val message = UiText.StringResource(
                                    id = R.string.you_have_chosen_s,
                                    args = arrayOf(sword.name)
                                ).asString(context)
                                onAction(
                                    GamePlayAction.OnSwipeRightClick(
                                        selectedSword = sword,
                                        message = message
                                    )
                                )
                            }
                        }
                        if(gamePlayState.suggestedSwords.isEmpty()) {
                            onAction(GamePlayAction.OnChooseDone)
                        }
                    }
                }
            }
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 32.dp)
                    .fillMaxWidth(),
            ) {
                Hint(gamePlayState.hint)
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    King3000CircleButton(
                        onClick = {
                            scope.launch {
                                val last = suggestedSwords.reversed()
                                    .firstOrNull {
                                        it.second.offset.value == Offset(0f, 0f)
                                    }?.second
                                last?.swipe(Direction.Left)
                            }
                        },
                        icon = Icons.Rounded.Close
                    )
                    King3000CircleButton(
                        onClick = {
                            scope.launch {
                                val last = suggestedSwords.reversed()
                                    .firstOrNull {
                                        it.second.offset.value == Offset(0f, 0f)
                                    }?.second
                                last?.swipe(Direction.Right)
                            }
                        },
                        icon = Icons.Rounded.Favorite
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun GamePlayScreenPreview() {
    King3000Theme {
        GamePlayScreen(
            gamePlayState = GamePlayState(
                warrior = Warrior(
                    characteristic = Utils.randomWarriorCharacteristicValue(),
                    physicalPower = Utils.randomWarriorPhysicalPowerValue(),
                    drawableResId = R.drawable.wooden_sword
                ),
                devil = Devil(
                    power = Utils.randomDevilPower()
                ),
                suggestedSwordState = emptyList()
            ),
            onAction = {

            }
        )
    }
}