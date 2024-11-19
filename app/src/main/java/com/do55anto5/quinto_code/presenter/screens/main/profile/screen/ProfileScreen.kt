package com.do55anto5.quinto_code.presenter.screens.main.profile.screen


import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.input.EditFieldType
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.image.ImageUI
import com.do55anto5.quinto_code.presenter.components.snackbar.FeedbackUI
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.components.top_app_bar.TopAppBarUI
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.OnSave
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.OnValueChange
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.ResetErrorState
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel.ProfileViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsState()
    val action = viewModel::submitAction
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var uriSaveable by rememberSaveable { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uriSaveable = uri
    }

    LaunchedEffect(state.hasFeedBack, uriSaveable) {
        if (uriSaveable != null) {
            scope.launch {
                action(
                    ProfileAction.OnImagePick(
                        context = context,
                        currentImage = uriSaveable
                    )
                )
            }
        }

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

    ProfileContent(
        state = state,
        action = action,
        navigateBack = navigateBack,
        galleryLauncher = galleryLauncher,
        snackbarHostState = snackbarHostState
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateBack: () -> Unit,
    snackbarHostState: SnackbarHostState,
    galleryLauncher: ManagedActivityResultLauncher<String, Uri?>
) {
    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                TopAppBarUI(
                    title = stringResource(id = R.string.top_bar_label_profile_screen),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { navigateBack() }
                )
                HorizontalDivider(
                    color = QuintoCodeTheme.colorScheme.dividerColor,
                )
            }
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
        containerColor = QuintoCodeTheme.colorScheme.backgroundColor,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(QuintoCodeTheme.colorScheme.backgroundColor)
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Box(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 8.dp),
                        content = {
                            if (state.compressedImage?.first != null) {
                                ImageUI(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .clip(RoundedCornerShape(60.dp))
                                        .border(
                                            width = 2.dp,
                                            color = QuintoCodeTheme.colorScheme.defaultColor,
                                            shape = RoundedCornerShape(110.dp)
                                        )
                                        .size(120.dp),
                                    imageModel = state.compressedImage.first!!,
                                    contentScale = ContentScale.Crop,
                                    onClick = { galleryLauncher.launch("image/*") },
                                    isLoading = state.isLoading
                                )
                            } else {
                                ImageUI(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .clip(RoundedCornerShape(60.dp))
                                        .border(
                                            width = 2.dp,
                                            color = QuintoCodeTheme.colorScheme.defaultColor,
                                            shape = RoundedCornerShape(110.dp)
                                        )
                                        .size(120.dp),
                                    imageModel = state.photo ?: "",
                                    contentScale = ContentScale.Crop,
                                    onClick = { galleryLauncher.launch("image/*") },
                                    isLoading = state.isLoading
                                )
                                Icon(
                                    modifier = Modifier
                                        .align(BottomEnd),
                                    painter = painterResource(id = R.drawable.ic_camera_fill),
                                    contentDescription = stringResource(R.string.icon_description_edit_label_profile_screen),
                                    tint = QuintoCodeTheme.colorScheme.defaultColor
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    TextFieldUI(
                        value = state.name,
                        label = stringResource(R.string.text_field_label_name_profile_screen),
                        placeholder = state.name,
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_pencil_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        enabled = state.isLoading.not(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            action(
                                OnValueChange(
                                    type = EditFieldType.NAME,
                                    value = it
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    TextFieldUI(
                        value = state.surname,
                        label = stringResource(R.string.text_field_label_surname_profile_screen),
                        placeholder = state.surname,
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_pencil_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        enabled = state.isLoading.not(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = {
                            action(
                                OnValueChange(
                                    type = EditFieldType.SURNAME,
                                    value = it
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    TextFieldUI(
                        value = state.city,
                        label = stringResource(R.string.text_field_label_city_profile_screen),
                        placeholder = state.city,
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_pencil_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        enabled = state.isLoading.not(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        onValueChange = {
                            action(
                                OnValueChange(
                                    type = EditFieldType.CITY,
                                    value = it
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    TextFieldUI(
                        value = state.email,
                        label = stringResource(R.string.text_field_label_email_profile_screen),
                        placeholder = state.email,
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        enabled = false,
                        onValueChange = {
                            action(
                                OnValueChange(
                                    type = EditFieldType.CITY,
                                    value = it
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    PrimaryButton(
                        text = stringResource(R.string.text_button_edit_profile_screen),
                        isLoading = state.isLoading,
                        onClick = { action(OnSave) }
                    )
                }
            )
        }
    )
    BackHandler {
        navigateBack()
        Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
    }
}

@PreviewLightDark
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        navigateBack = {}
    )
}