package com.pome.king3000.features.game_play.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pome.king3000.R
import com.pome.king3000.components.King3000ActionButton
import com.pome.king3000.components.King3000OutlinedActionButton
import com.pome.king3000.features.components.GifImage
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun GameOverDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    @DrawableRes drawableGifResId: Int,
    onCancelClick: () -> Unit,
    onReviewClick: () -> Unit,
) {
    var isDismiss by remember { mutableStateOf(false) }
    if (!isDismiss) {
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                GifImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    drawableGifResId = drawableGifResId
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    King3000OutlinedActionButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = stringResource(id = R.string.quit),
                        isLoading = false
                    ) {
                        isDismiss = true
                        onCancelClick()
                    }
                    Spacer(modifier = modifier.width(8.dp))
                    King3000ActionButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text =  stringResource(id = R.string.review),
                        isLoading = false
                    ) {
                        isDismiss = true
                        onReviewClick()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGameOverDialog() {
    King3000Theme {
        GameOverDialog(
            title = stringResource(id = R.string.vic).uppercase(),
            description = stringResource(id = R.string.you_re_a_victory),
            drawableGifResId = R.drawable.happy,
            onCancelClick = {},
            onReviewClick = {}
        )
    }
}