package com.example.it_da.ui.screen.signup.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.ItdaPlaceholderTextColor
import com.example.it_da.ui.theme.ItdaInputBorderGray
import com.example.it_da.ui.theme.DotSans

private val SignUpFieldLabelWeight = FontWeight.Medium
private val SignUpInputTextWeight = FontWeight(600)

// Draws a labeled rounded input that hides its example text while focused or filled.
@Composable
fun SignUpOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val inputTextStyle = TextStyle(
        fontFamily = DotSans,
        fontWeight = SignUpInputTextWeight,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Column(modifier = modifier.fillMaxWidth()) {
        SignUpFieldLabel(text = label)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(
                    width = 1.2.dp,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 11.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = inputTextStyle,
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = placeholder,
                            color = ItdaPlaceholderTextColor,
                            style = inputTextStyle.copy(color = ItdaPlaceholderTextColor)
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

// Draws the field label shared by all sign-up form inputs.
@Composable
fun SignUpFieldLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(bottom = 12.dp),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = SignUpFieldLabelWeight,
            fontSize = 16.sp,
            lineHeight = 16.sp
        )
    )
}
