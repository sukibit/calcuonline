package com.oliversolutions.dev.calcuonline.presentation.composables.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp

@Composable
fun ClickableImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    contentDescription: String = ""
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable(onClick = onClick)
            .height(30.dp)
            .width(30.dp)
    )
}