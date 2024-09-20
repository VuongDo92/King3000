package com.pome.king3000.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pome.king3000.R
import com.pome.king3000.data.Devil
import com.pome.king3000.data.GameResult
import com.pome.king3000.data.SwordStore
import com.pome.king3000.data.Warrior
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.features.game_review.components.Title
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun GamePlayInfo(
    modifier: Modifier = Modifier,
    coilWidth: Int = 600,
    coilHeight: Int = 600,
    gifHeight: Dp = 300.dp,
    gameResult: GameResult? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GifImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                size = coil.size.Size(
                    coilWidth,
                    coilHeight
                ),
                height = 300.dp,
                drawableGifResId = R.drawable.warriors
            )
            Spacer(modifier = Modifier.width(32.dp))
            GifImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                size = coil.size.Size(
                    coilWidth,
                    coilHeight
                ),
                height = gifHeight,
                drawableGifResId = R.drawable.devils
            )
        }
        if(gameResult != null) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,)
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
//                    Text(
//                        text = gameResult.title.uppercase(),
//                        style = MaterialTheme.typography.headlineSmall,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
                    Title(
                        title = stringResource(id = R.string.name),
                        subtitle = gameResult.warrior.name ?: ""
                    )
                    Title(
                        title = stringResource(id = R.string.characteristic),
                        subtitle = gameResult.warrior.characteristic.toString()
                    )
                    Title(
                        title = stringResource(id = R.string.capacity),
                        subtitle = gameResult.warrior.physicalPower.toString()
                    )
                    Title(
                        title = stringResource(id = R.string.weapon_quanity),
                        subtitle = gameResult.chosenSword.size.toString()
                    )
                    Title(
                        title = stringResource(id = R.string.weapon_quality),
                        subtitle = gameResult.weaponQuality.toString()
                    )
                    Title(
                        title = stringResource(id = R.string.final_power),
                        subtitle = gameResult.warriorFinalPower.toString()
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = stringResource(id = R.string.devil_power),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(text = "${gameResult.devil.power}",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun PreviewGamePlayInfo() {
    King3000Theme {
        GamePlayInfo(
            gameResult = GameResult(
                warrior = Warrior(
                    characteristic = Utils.randomWarriorCharacteristicValue(),
                    physicalPower = Utils.randomWarriorPhysicalPowerValue(),
                    drawableResId = R.drawable.wooden_sword
                ),
                devil = Devil(
                    power = Utils.randomDevilPower()
                ),
                title = "VICTORY",
                chosenSword = SwordStore,
                warriorFinalPower = 100,
                weaponQuality = 19
            )
        )
    }
}
