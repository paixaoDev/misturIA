package com.paixao.dev.misturia.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.misturia.R

@Composable
fun IngredientListItem(
    ingredientTypePainter: Painter = painterResource(id = R.drawable.ic_launcher_foreground),
    ingredientName: String = "",
    isEditable: Boolean = false,
) {
    IngredientListItemComposable(
        ingredientTypePainter = ingredientTypePainter,
        ingredientName = ingredientName,
        isEditable = isEditable
    )
}


@Composable
private fun IngredientListItemComposable(
    ingredientTypePainter: Painter,
    ingredientName: String,
    isEditable: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageEditable(
                selectedImage = ingredientTypePainter,
                isEditable = isEditable
            ) {

            }
            Spacer(modifier = Modifier.size(3.dp))
            TextEditable(
                text = ingredientName,
                isEditable = isEditable
            ) {

            }
        }

        if (isEditable) {
            Box(
                modifier = Modifier.padding(5.dp)
            ) {
                TwoIconButtons(
                    onLeftClick = {

                    },
                    onRightClick = {

                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun IngredientListItemPreview() {
    MaterialTheme {
        IngredientListItem(
            ingredientTypePainter = painterResource(id = R.drawable.ic_launcher_background),
            ingredientName = "Ingredient",
            isEditable = true
        )
    }
}