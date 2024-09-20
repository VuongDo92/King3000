package com.pome.king3000.features.components


import android.os.Build.VERSION.SDK_INT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.pome.king3000.R
import com.pome.king3000.ui.theme.King3000Theme

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    size: Size? = null,
    height: Dp? = null,
    @DrawableRes drawableGifResId: Int,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(context)
                .data(data = drawableGifResId)
                .apply(
                    block = {
                        size?.let {
                            size(it)  // Custom size if provided
                        } ?: size(Size.ORIGINAL)  // Default to original size
                    }
                )
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = if (height == null) {
            modifier.fillMaxWidth()
        } else modifier
            .fillMaxWidth()
            .height(300.dp),
    )
}

@Preview
@Composable
private fun PreviewGifImage() {
    King3000Theme {
        GifImage(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            drawableGifResId = R.drawable.happy
        )
    }
}