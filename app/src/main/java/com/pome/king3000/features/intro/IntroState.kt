package com.pome.king3000.features.intro

data class IntroState(
    val currentPage: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val canLoadMore: Boolean = false,
    val reachedBottom: Boolean = false,
)
