package com.pome.king3000

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pome.king3000.features.game_play.GamePlayScreenRoot
import com.pome.king3000.features.intro.IntroScreenRoot


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
            "intro"
        ) {
            IntroScreenRoot(
                onPlayClick = {
                    navController.navigate("game_play") {
                        popUpTo("intro") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
        composable(
            "game_play"
        ) {
            GamePlayScreenRoot()
        }
    }
}