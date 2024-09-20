package com.pome.king3000.features.game_review.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pome.king3000.R
import com.pome.king3000.data.Sword
import com.pome.king3000.data.utils.Utils
import com.pome.king3000.ui.theme.King3000DarkRed
import com.pome.king3000.ui.theme.King3000Gray40
import com.pome.king3000.ui.theme.King3000Pinky
import com.pome.king3000.ui.theme.King3000Theme
import com.pome.king3000.ui.theme.King3000White


@Composable
fun SwordReviewCard(
    modifier: Modifier,
    sword: Sword,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(sword.drawableResId),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .align(Alignment.Top)
                        .padding(end = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Text(
                            text = sword.name,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start,
                        )
                        HorizontalDivider(
                            color = King3000Gray40,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Title(
                            icon = {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(12.dp)
                                        .aspectRatio(1f),
                                    painter = painterResource(R.drawable.swords),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(King3000DarkRed)
                                )
                            },
                            title = stringResource(
                                id = R.string.attack_value_s,

                            ),
                            subtitle = "${sword.attackValue}"
                        )
                        Title(
                            icon = {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(12.dp)
                                        .aspectRatio(1f),
                                    painter = painterResource(R.drawable.swords),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                )
                            },
                            title = stringResource(
                                id = R.string.attack_weight_s
                            ),
                            subtitle = "${sword.attackWeight}"
                        )
                        HorizontalDivider(
                            thickness = 4.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Title(
                            icon = {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(12.dp)
                                        .aspectRatio(1f),
                                    painter = painterResource(R.drawable.shield),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(King3000Pinky)
                                )
                            },
                            title = stringResource(
                                id = R.string.defend_value_s,

                            ),
                            subtitle = "${sword.defendValue}"
                        )
                        Title(
                            icon = {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(12.dp)
                                        .aspectRatio(1f),
                                    painter = painterResource(R.drawable.shield),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                )
                            },
                            title = stringResource(
                                id = R.string.defend_weight_s,

                            ),
                            subtitle = "${sword.defendWeight}"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSwordReviewCard() {
    King3000Theme {
        SwordReviewCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(
                    0.3f
                ),
            sword = Sword(
                name = "Erlich Bachman",
                drawableResId = R.drawable.reaper_sword,
                attackValue = Utils.randomSwordAttackValue(),
                attackWeight = Utils.randomSwordAttackWeight(),
                defendValue = Utils.randomSwordDefendValue(),
                defendWeight = Utils.randomSwordDefendWeight(),
            )
        )
    }
}
