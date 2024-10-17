package com.do55anto5.quinto_code.presenter.components.text_field


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@Composable
fun TextFieldUI(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    mLeadingIcon: @Composable (() -> Unit)? = null,
    mTrailingIcon: @Composable (() -> Unit)? = null,
    requireKeyboardFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        val focusRequester = remember { FocusRequester() }

        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .border(
                    width = 1.dp,
                    color = if (isError) {
                        QuintoCodeTheme.colorScheme.defaultColor
                    } else {
                        QuintoCodeTheme.colorScheme.transparentColor
                    },
                    shape = RoundedCornerShape(12.dp)
                ),
            enabled = enabled,
            label = {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        letterSpacing = 0.2.sp
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        letterSpacing = 0.2.sp
                    )
                )
            },
            leadingIcon = mLeadingIcon,
            trailingIcon = mTrailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = QuintoCodeTheme.colorScheme.textFieldBackGroundColor,
                focusedContainerColor = QuintoCodeTheme.colorScheme.textFieldBackGroundColor,
                errorContainerColor = QuintoCodeTheme.colorScheme.alphaDefaultColor,
                unfocusedIndicatorColor = QuintoCodeTheme.colorScheme.transparentColor,
                focusedIndicatorColor = QuintoCodeTheme.colorScheme.transparentColor,
                errorIndicatorColor = QuintoCodeTheme.colorScheme.transparentColor,
                unfocusedTextColor = QuintoCodeTheme.colorScheme.textColor,
                focusedTextColor = QuintoCodeTheme.colorScheme.textColor,
                errorTextColor = QuintoCodeTheme.colorScheme.textColor,
                unfocusedLabelColor = QuintoCodeTheme.colorScheme.greyscale500Color,
                focusedLabelColor = QuintoCodeTheme.colorScheme.greyscale500Color,
                errorLabelColor = QuintoCodeTheme.colorScheme.greyscale500Color,
                unfocusedLeadingIconColor = QuintoCodeTheme.colorScheme.greyscale500Color,
                focusedLeadingIconColor = QuintoCodeTheme.colorScheme.blackColor,
                errorLeadingIconColor = QuintoCodeTheme.colorScheme.defaultColor,
                unfocusedTrailingIconColor = QuintoCodeTheme.colorScheme.greyscale500Color,
                focusedTrailingIconColor = QuintoCodeTheme.colorScheme.blackColor,
                errorTrailingIconColor = QuintoCodeTheme.colorScheme.defaultColor,
                focusedPlaceholderColor = QuintoCodeTheme.colorScheme.greyscale500Color
            )
        )

        if (requireKeyboardFocus) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TextFieldUIPreview() {

    var textValue by remember {
        mutableStateOf("")
    }

    QuintoCodeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QuintoCodeTheme.colorScheme.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldUI(
                modifier = Modifier
                    .padding(16.dp),
                value = textValue,
                label = "Name",
                placeholder = "Enter your name",
                mLeadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_email_fill),
                        contentDescription = null,
                        tint = QuintoCodeTheme.colorScheme.greyscale500Color
                    )
                },
                mTrailingIcon = {
                    IconButton(
                        onClick = { },
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.ic_show),
                                contentDescription = null
                            )
                        }
                    )
                },
                enabled = true,
                onValueChange = {
                    textValue = it
                }
            )
        }
    }
}