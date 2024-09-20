package com.pome.king3000

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.pome.king3000.data.GameResult
import com.pome.king3000.features.game_explanation.GameExplanationScreenRoot
import com.pome.king3000.features.game_play.GamePlayScreenRoot
import com.pome.king3000.features.game_review.GameReviewScreenRoot
import com.pome.king3000.features.intro.IntroScreenRoot
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        mainGraph(navController)
    }
}

private fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "main"
    ) {
        composable(
            route = "intro"
        ) {
            IntroScreenRoot(
                onNextClick = { warriorName ->
                    navController.navigate(
                        route = "game_play/${warriorName}"
                    ) {
                        popUpTo("intro") {
                            inclusive = false
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onReadMoreClick = {
                    navController.navigate(
                        route = "game_explanation"
                    ) {
                        popUpTo("intro") {
                            inclusive = false
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
        composable(
            route = "game_explanation"
        ) {
            GameExplanationScreenRoot(
                onCloseClick = {
                    navController.popBackStack(
                        route = "intro",
                        inclusive = false,
                    )
                }
            )
        }
        composable(
            route = "game_play/{warriorName}",
            arguments = listOf(
                navArgument(name = "warriorName") {
                    type = NavType.StringType
                }
            )
        ) {
            val warriorName = it.arguments?.getString("warriorName") ?: ""
            GamePlayScreenRoot(
                warriorName = warriorName,
                onQuitClick = {
                    navController.popBackStack(
                        route = "intro",
                        inclusive = false,
                    )
                },
                onReviewClick = { gameResult: GameResult ->
                    val gameResultStr = Json.encodeToString(gameResult)
                    navController.navigate("game_review/${gameResultStr}") {
                        popUpTo("intro") {
                            inclusive = false
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
        composable(
            route = "game_review/{gameResult}",
            arguments = listOf(
                navArgument("gameResult") {
                    type = NavType.StringType
                }
            )
        ) {
            val gameResultString = it.arguments?.getString("gameResult") ?: ""
            val gameResult = Json.decodeFromString<GameResult>(gameResultString)

            GameReviewScreenRoot(
                gameResult = gameResult,
                onQuitClick = {
                    navController.popBackStack(
                        route = "intro",
                        inclusive = false,
                    )
                }
            )
        }
    }
}