package com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.input.InputType.EMAIL
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.snackbar.FeedbackUI
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.components.top_app_bar.TopAppBarUI
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action.ForgotPasswordAction
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action.ForgotPasswordAction.OnSend
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action.ForgotPasswordAction.OnValueChange
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.action.ForgotPasswordAction.ResetErrorState
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.state.ForgotPasswordState
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.viewmodel.ForgotPasswordViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<ForgotPasswordViewModel>()
    val state by viewModel.state.collectAsState()

    ForgotPasswordContent(
        onBackPressed = onBackPressed,
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun ForgotPasswordContent(
    onBackPressed: () -> Unit,
    state: ForgotPasswordState,
    action: (ForgotPasswordAction) -> Unit
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.hasFeedBack) {
        scope.launch {
            val result = snackbarHostState
                .showSnackbar(
                    message = context.getString(
                        state.feedbackUI?.second ?: R.string.error_generic
                    )
                )

            if (result == SnackbarResult.Dismissed) {
                action(ResetErrorState)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarUI(
                onClick = { onBackPressed() }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    state.feedbackUI?.let { feedbackUI ->
                        FeedbackUI(
                            message = snackbarData.visuals.message,
                            type = feedbackUI.first
                        )
                    }
                }
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
                        text = stringResource(R.string.label_title_forgot_password_screen),
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
                        value = state.email,
                        label = stringResource(R.string.label_input_email_forgot_password_screen),
                        placeholder = stringResource(R.string.placeholder_input_email_forgot_password_screen),
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        onValueChange = {
                            action(
                                OnValueChange(
                                    value = it,
                                    type = EMAIL
                                )

                            )
                        }
                    )

                    Spacer(Modifier.height(20.dp))

                    Spacer(Modifier.height(20.dp))

                    PrimaryButton(
                        text = stringResource(R.string.button_send_email_forgot_password_screen),
                        isLoading = false,
                        enabled = state.enableSendButton,
                        onClick = { action(OnSend) }
                    )
                }
            )
        }
    )

}

@PreviewLightDark
@Composable
private fun ForgotPasswordScreenPreview() {
    ForgotPasswordContent(
        onBackPressed = {},
        state = ForgotPasswordState(),
        action = {}
    )
}