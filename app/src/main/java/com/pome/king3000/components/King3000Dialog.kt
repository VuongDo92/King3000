package com.pome.king3000.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun King3000Dialog(
    title: String,
    onDismiss: () -> Unit,
    description: String,
    primaryButton: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    secondaryButton: @Composable RowScope.() -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                secondaryButton()
                primaryButton()
            }
        }
    }
}


@Preview
@Composable
private fun PreViewKing3000Dialog() {
    King3000Theme {
        King3000Dialog(
            title = "Title",
            onDismiss = {

            },
            description = "Description",
            primaryButton = {
                King3000ActionButton(
                    text = "Cancel",
                    isLoading = false,
                    onClick = {

                    },
                    modifier = Modifier.weight(1f)
                )
            },
            secondaryButton = {
                King3000OutlinedActionButton(
                    text = "Continue",
                    isLoading = false,
                    onClick = {

                    },
                    modifier = Modifier.weight(1f)
                )
            }
        )
    }
}