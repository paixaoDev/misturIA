package com.paixao.dev.misturia.composable.components

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paixao.dev.misturia.ui.theme.MisturIATheme
import com.paixao.dev.misturia.ui.theme.timmana


@Composable
fun AppName(
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    shinyTextColor: Color = MaterialTheme.colorScheme.tertiary,
    shinyColor: Color = MaterialTheme.colorScheme.tertiary
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Mistur",
            modifier = Modifier.height(28.dp),
            style = textStyle.copy(fontFamily = timmana, lineHeight = 0.sp, letterSpacing = 0.sp)
        )
        AppNameCompose(
            textStyle = textStyle,
            shinyTextColor = shinyTextColor,
            shinyColor = shinyColor,
        )
    }
}

@Composable
private fun AppNameCompose(
    textStyle: TextStyle,
    shinyTextColor: Color,
    shinyColor: Color,
) {
    Box {
        Text(
            text = "IA",
            color = shinyTextColor,
            style = textStyle.copy(fontWeight = FontWeight.ExtraBold, lineHeight = 0.sp),
        )

        val shinyColors = listOf(
            Color.Magenta .copy(alpha = 0.6f),
            shinyColor.copy(alpha = 0.0f),
            Color.White.copy(alpha = 0.6f),
            shinyColor.copy(alpha = 0.0f),
            Color.Yellow.copy(alpha = 0.6f),
            Color.Yellow.copy(alpha = 0.6f),
            shinyColor.copy(alpha = 0.0f),
            Color.Blue.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "InfiniteTransition")
        val translateAnim = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = "FloatAnimation"
        )

        val brush = Brush.linearGradient(
            colors = shinyColors,
            start = Offset.Zero,
            end = Offset(x = translateAnim.value, y = translateAnim.value)
        )

        Text(
            text = "IA",
            color = shinyTextColor,
            style = textStyle.copy(
                lineHeight = 1.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = brush
            )
        )
    }
}


@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Dynamic Color",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    showBackground = true
)
@Composable
private fun TitleValuationPreview() {
    MisturIATheme {
        AppName()
    }
}