package com.paixao.dev.misturia.composable.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TwoIconButtons(
    leftIconColor: Color = MaterialTheme.colorScheme.primary,
    leftIcon: Painter = painterResource(id = android.R.drawable.checkbox_on_background),
    leftContentDescription: String = "",
    onLeftClick: () -> Unit = {},
    rightIconColor: Color = MaterialTheme.colorScheme.error,
    rightIcon: Painter = painterResource(id = android.R.drawable.ic_delete),
    rightContentDescription: String = "",
    onRightClick: () -> Unit = {}
) {
    TwoIconButtonsComposable(
        leftIconColor = leftIconColor,
        leftIcon = leftIcon,
        leftContentDescription = leftContentDescription,
        onLeftClick = onLeftClick,
        rightIconColor = rightIconColor,
        rightIcon = rightIcon,
        rightContentDescription = rightContentDescription,
        onRightClick = onRightClick
    )
}

@Composable
private fun TwoIconButtonsComposable(
    leftIconColor: Color,
    leftIcon: Painter,
    leftContentDescription: String,
    onLeftClick: () -> Unit,
    rightIconColor: Color,
    rightIcon: Painter,
    rightContentDescription: String,
    onRightClick: () -> Unit
) {
    Row {
        Button(
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = leftIconColor),
            contentPadding = PaddingValues(0.dp),
            onClick = { onLeftClick.invoke() }
        ) {
            Icon(painter = leftIcon, contentDescription = leftContentDescription)
        }
        Spacer(modifier = Modifier.size(3.dp))
        Button(
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = rightIconColor),
            contentPadding = PaddingValues(0.dp),
            onClick = { onRightClick.invoke() }
        ) {
            Icon(painter = rightIcon, contentDescription = rightContentDescription)
        }
    }
}

@Composable
@Preview
fun TwoIconButtonsPreview (){
    MaterialTheme{
        TwoIconButtons()
    }
}