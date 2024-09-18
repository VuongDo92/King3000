package com.pome.king3000.features.game_play


data class GamePlayState(
    val currentPage: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val canLoadMore: Boolean = false,
    val reachedBottom: Boolean = false,
)