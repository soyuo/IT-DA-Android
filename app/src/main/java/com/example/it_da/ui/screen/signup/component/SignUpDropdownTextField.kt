package com.example.it_da.ui.screen.signup.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.R
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaInputBorderGray
import com.example.it_da.ui.theme.ItdaPlaceholderTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val SignUpDropdownInputTextWeight = FontWeight(600)

// Draws a rounded input with a button arrow reserved for a later dropdown screen.
@Composable
fun SignUpDropdownTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onArrowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val inputTextStyle = TextStyle(
        fontFamily = DotSans,
        fontWeight = SignUpDropdownInputTextWeight,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Column(modifier = modifier.fillMaxWidth()) {
        SignUpFieldLabel(text = label)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(
                    width = 1.2.dp,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 15.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = inputTextStyle,
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
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

            IconButton(
                onClick = onArrowClick,
                modifier = Modifier.size(44.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_down_arrow),
                    contentDescription = "open options",
                    tint = ItdaSecondaryTextColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
