package com.paixao.dev.misturia.composable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageEditable(
    selectedImage: Painter,
    isEditable: Boolean = false,
    imageSelectionContent: @Composable () -> Unit
) {
    ImageEditableComposable(
        image = selectedImage,
        isEditable = isEditable,
        imageSelectionContent = imageSelectionContent
    )
}


@Composable
private fun ImageEditableComposable(
    image: Painter,
    isEditable: Boolean,
    imageSelectionContent: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.size(30.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable {
                    if (isEditable) {
                        imageSelectionContent
                    }
                }
        )

        if (isEditable) {
            Image(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = android.R.drawable.ic_menu_edit),
                contentDescription = ""
            )
        }
    }

}