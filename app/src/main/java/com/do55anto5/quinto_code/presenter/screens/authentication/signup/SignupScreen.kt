package com.do55anto5.quinto_code.presenter.screens.authentication.signup


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.button.SocialButton
import com.do55anto5.quinto_code.presenter.components.divider.HorizontalDividerWithText
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.components.top_app_bar.TopAppBarUI
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@Composable
fun SignupScreen(
    onBackPressed: () -> Unit
) {
    SignupContent(
        onBackPressed = { }
    )
}

@Composable
fun SignupContent(
    onBackPressed: () -> Unit
) {

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBarUI(
                onClick = {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(QuintoCodeTheme.colorScheme.backgroundColor)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(110.dp))
                            .border(
                                width = 2.dp,
                                color = QuintoCodeTheme.colorScheme.defaultColor,
                                shape = RoundedCornerShape(110.dp)
                            )
                            .size(220.dp),
                    )

                    Spacer(Modifier.height(48.dp))

                    Text(
                        text = stringResource(R.string.label_title_signup_screen),
                        style = TextStyle(
                            fontSize = 32.sp,
                            lineHeight = 38.4.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            color = QuintoCodeTheme.colorScheme.textColor,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(Modifier.height(48.dp))

                    // EMAIL INPUT FIELD
                    TextFieldUI(
                        modifier = Modifier,
                        value = emailValue,
                        label = stringResource(R.string.label_input_email_signup_screen),
                        placeholder = stringResource(R.string.placeholder_input_email_signup_screen),
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        onValueChange = {
                            emailValue = it
                        }
                    )

                    Spacer(Modifier.height(20.dp))

                    // PASSWORD INPUT FIELD
                    TextFieldUI(
                        modifier = Modifier,
                        value = passwordValue,
                        label = stringResource(R.string.label_input_password_signup_screen),
                        placeholder = stringResource(R.string.placeholder_input_password_signup_screen),
                        visualTransformation =
                        if (showPassword) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_password_mine),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        mTrailingIcon = {
                            IconButton(
                                onClick = {
                                    showPassword = !showPassword
                                },
                                content = {
                                    if (passwordValue.isNotEmpty()) {
                                        Icon(
                                            painter =
                                            if (showPassword) {
                                                painterResource(R.drawable.ic_hide)
                                            } else {
                                                painterResource(R.drawable.ic_show)
                                            },
                                            contentDescription = null,
                                            tint = QuintoCodeTheme.colorScheme.greyscale500Color
                                        )
                                    }
                                }
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        onValueChange = {
                            passwordValue = it
                        }
                    )

                    Spacer(Modifier.height(20.dp))

                    PrimaryButton(
                        text = stringResource(R.string.label_button_signup_screen),
                        isLoading = false,
                        enabled = true,
                        onClick = {}
                    )

                    Spacer(Modifier.height(20.dp))

                    HorizontalDividerWithText(
                        text = stringResource(R.string.label_or_signup_screen)
                    )

                    Spacer(Modifier.height(20.dp))

                    // SOCIAL BUTTONS
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            SocialButton(
                                icon = painterResource(id = R.drawable.ic_google),
                                onClick = {}
                            )
                        }
                    )

                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            Text(
                                text = stringResource(id = R.string.label_sign_up_question_signup_screen),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = UrbanistFamily,
                                    color = QuintoCodeTheme.colorScheme.textColor,
                                    textAlign = TextAlign.Right,
                                    letterSpacing = 0.2.sp
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = stringResource(id = R.string.label_sign_in_signup_screen),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = QuintoCodeTheme.colorScheme.defaultColor,
                                    textAlign = TextAlign.Right,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun SignupScreenPreview() {
    QuintoCodeTheme {
        SignupContent(onBackPressed = {})
    }
}