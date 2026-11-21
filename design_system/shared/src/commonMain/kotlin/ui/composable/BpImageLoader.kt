package com.beepbeep.designSystem.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.model.ImageEvent
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.rememberImageAction
import com.seiko.imageloader.rememberImageActionPainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BpImageLoader(
    imageUrl: String,
    errorPlaceholderImageUrl: String,
    modifier: Modifier = Modifier,
    showLoadingState: Boolean = true,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeHolderScale: ContentScale = ContentScale.Crop,
    painter: Painter? = null,
) {
    val action = rememberImageAction(imageUrl).value
    val usePlaceholder = painter == null &&
        (action is ImageEvent || action is ImageResult.Error || action is ImageResult.Source)
    val imagePainter = painter ?: if (usePlaceholder) {
        painterResource(errorPlaceholderImageUrl)
    } else {
        rememberImageActionPainter(action)
    }
    Image(
        modifier = modifier.fillMaxSize(),
        painter = imagePainter,
        contentScale = if (usePlaceholder) placeHolderScale else contentScale,
        contentDescription = contentDescription,
    )
}
