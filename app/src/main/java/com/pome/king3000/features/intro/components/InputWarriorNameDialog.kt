package com.pome.king3000.features.intro.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pome.king3000.R
import com.pome.king3000.components.King3000ActionButton
import com.pome.king3000.components.King3000TextField
import com.pome.king3000.ui.theme.King3000Theme


@Composable
fun InputWarriorNameDialog(
    modifier: Modifier = Modifier,
    onNextClick: (String) -> Unit
) {
    var isDismiss by remember { mutableStateOf(false) }

    val textFieldState = rememberTextFieldState()

    if(!isDismiss) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.warrior_name),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                King3000TextField(
                    state = textFieldState,
                    hint = stringResource(id = R.string.arman),
                    title = stringResource(id = R.string.warrior_s_name),
                    startIcon = null,
                    endIcon = null
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    King3000ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = stringResource(id = R.string.next), isLoading = false
                    ) {
                        Log.i("Text Done", "${textFieldState.text.toString()}")
                        onNextClick(textFieldState.text.toString())
                        isDismiss = true
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewInputWarriorNameDialog() {
    King3000Theme {
        InputWarriorNameDialog(modifier = Modifier.fillMaxWidth()) {

        }
    }
}