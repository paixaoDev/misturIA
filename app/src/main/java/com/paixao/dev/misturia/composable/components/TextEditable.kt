package com.paixao.dev.misturia.composable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextEditable(
    modifier: Modifier = Modifier,
    text: String = "",
    isEditable: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onChange: (String) -> Unit = {}
) {
    TextEditableComposable(
        text = text,
        isEditable = isEditable,
        onChange = onChange,
        modifier = modifier,
        textStyle = textStyle
    )
}

@Composable
private fun TextEditableComposable(
    text: String,
    isEditable: Boolean,
    onChange: (String) -> Unit,
    modifier: Modifier,
    textStyle: TextStyle
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        when (isEditable) {
            true -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        value = text,
                        textStyle = textStyle,
                        onValueChange = { value ->
                            onChange(value)
                        },
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outlineVariant,
                                        shape = MaterialTheme.shapes.extraSmall
                                    )
                                    .padding(horizontal = 10.dp)
                            ) {
                                innerTextField()
                            }
                        }
                    )
                    Image(
                        modifier = Modifier.width(30.dp).height(12.dp),
                        painter = painterResource(id = android.R.drawable.ic_menu_edit),
                        contentDescription = ""
                    )
                }
            }

            else -> {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    style = textStyle
                )
            }
        }
    }
}

@Composable
@Preview
fun TextEditablePreview (){
    MaterialTheme{
        TextEditable()
    }
}