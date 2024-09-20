package com.pome.king3000.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.pome.king3000.ui.theme.King3000Lavender
import com.pome.king3000.ui.theme.King3000Pinky
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(
                    listOf(
                        King3000Pinky,
                        King3000Lavender
                    )
                ))
                .systemBarsPadding()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if(hasToolbar) {
                        Modifier
                    } else {
                        Modifier.systemBarsPadding()
                    }
                )
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun GradientBackgroundPreview() {
    King3000Theme {
        GradientBackground(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}