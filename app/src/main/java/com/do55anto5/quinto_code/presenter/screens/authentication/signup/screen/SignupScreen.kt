package com.do55anto5.quinto_code.presenter.screens.authentication.signup.screen


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.input.InputType.EMAIL
import com.do55anto5.quinto_code.core.enums.input.InputType.PASSWORD
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.button.SocialButton
import com.do55anto5.quinto_code.presenter.components.divider.HorizontalDividerWithText
import com.do55anto5.quinto_code.presenter.components.snackbar.FeedbackUI
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.components.top_app_bar.TopAppBarUI
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.action.GoogleSignInAction
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.state.GoogleSignInState
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.viewmodel.GoogleSignInViewModel
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction.OnPasswordVisibilityChange
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction.OnSignup
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction.OnValueChange
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.action.SignupAction.ResetErrorState
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.state.SignupState
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.viewmodel.SignupViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToAppScreen: () -> Unit,
    onBackPressed: () -> Unit
) {

    val viewModel = koinViewModel<SignupViewModel>()
    val googleSignInViewModel = koinViewModel<GoogleSignInViewModel>()
    val state by viewModel.state.collectAsState()
    val googleState by googleSignInViewModel.state.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val action = viewModel::submitAction

    LaunchedEffect(state.isAuthenticated, state.hasFeedBack, googleState == GoogleSignInState.IsAuthenticated(true)) {
        if (
            state.isAuthenticated ||
            googleState == GoogleSignInState.IsAuthenticated(true)
        ) {
            navigateToAppScreen()
        }

        if (state.hasFeedBack) {
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

        if (googleState == GoogleSignInState.Error()) {
            val errorState = googleState as GoogleSignInState.Error
            scope.launch {
                val result = errorState.feedbackUI?.let { context.getString(it.second) }?.let {
                    snackbarHostState
                        .showSnackbar(
                            message = it
                        )
                }

                if (result == SnackbarResult.Dismissed) {
                    googleSignInViewModel.submitAction(GoogleSignInAction.ResetErrorState)
                }
            }
        }
    }

    SignupContent(
        navigateToLoginScreen = navigateToLoginScreen,
        state = state,
        action = action,
        googleSignInAction = googleSignInViewModel::submitAction,
        onBackPressed = onBackPressed,
        snackbarHostState = snackbarHostState,
        context = context
    )
}

@Composable
private fun SignupContent(
    navigateToLoginScreen: () -> Unit,
    state: SignupState,
    action: (SignupAction) -> Unit,
    googleSignInAction: (GoogleSignInAction) -> Unit,
    onBackPressed: () -> Unit,
    snackbarHostState: SnackbarHostState,
    context: Context
) {

    val isLoadingButton = remember { mutableStateOf(false) }

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
                        value = state.email,
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
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
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

                    // PASSWORD INPUT FIELD
                    TextFieldUI(
                        modifier = Modifier,
                        value = state.password,
                        label = stringResource(R.string.label_input_password_signup_screen),
                        placeholder = stringResource(R.string.placeholder_input_password_signup_screen),
                        visualTransformation =
                        if (state.passwordVisibility) {
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
                            if (state.password.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        action(OnPasswordVisibilityChange)
                                    },
                                    content = {
                                        if (state.password.isNotEmpty()) {
                                            Icon(
                                                painter =
                                                if (state.passwordVisibility) {
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
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        onValueChange = {
                            action(
                                OnValueChange(
                                    value = it,
                                    type = PASSWORD
                                )
                            )
                        }
                    )

                    Spacer(Modifier.height(20.dp))

                    PrimaryButton(
                        text = stringResource(R.string.label_button_signup_screen),
                        isLoading = false,
                        enabled = state.enableSignupButton,
                        onClick = { action(OnSignup) }
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
                                isLoading = isLoadingButton.value,
                                onClick = {
                                    googleSignInAction(GoogleSignInAction.SignIn(context))
                                    isLoadingButton.value = true
                                }
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
                                modifier = Modifier
                                    .clickable { navigateToLoginScreen() },
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
        SignupContent(
            navigateToLoginScreen = {},
            state = SignupState(),
            action = {},
            googleSignInAction = {},
            onBackPressed = {},
            snackbarHostState = SnackbarHostState(),
            context = LocalContext.current
        )
    }
}