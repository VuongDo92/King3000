package com.pome.king3000.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.pome.king3000.King3000App
import com.pome.king3000.data.repository.GamePlayRepositoryImpl
import com.pome.king3000.domain.repository.GamePlayRepository
import com.pome.king3000.features.game_explanation.GameExplanationViewModel
import com.pome.king3000.features.game_play.GamePlayViewModel
import com.pome.king3000.features.game_review.GameReviewViewModel
import com.pome.king3000.features.intro.IntroViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "king3000_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    single<CoroutineScope> {
        (androidApplication() as King3000App).applicationScope
    }
    viewModelOf(::IntroViewModel)
    viewModelOf(::GamePlayViewModel)
    viewModelOf(::GameReviewViewModel)
    viewModelOf(::GameExplanationViewModel)

    singleOf(::GamePlayRepositoryImpl).bind<GamePlayRepository>()
}