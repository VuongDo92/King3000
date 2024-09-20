package com.pome.king3000.features.game_play.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pome.king3000.R
import com.pome.king3000.Scrim
import com.pome.king3000.data.Sword
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun SwordProfileCard(
    modifier: Modifier,
    sword: Sword,
) {
    Card(modifier.aspectRatio(1f)) {
        Box {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                painter = painterResource(sword.drawableResId),
                contentDescription = null
            )
            Scrim(Modifier.align(Alignment.BottomCenter))
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = sword.name,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProfileCard() {
    King3000Theme {
        SwordProfileCard(
            modifier = Modifier.fillMaxSize(),
            sword = Sword(
                name = "Erlich Bachman",
                drawableResId = R.drawable.blair_diamond_sword,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            )
        )
    }
}