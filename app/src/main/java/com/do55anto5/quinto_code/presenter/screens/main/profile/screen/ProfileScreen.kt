package com.do55anto5.quinto_code.presenter.screens.main.profile.screen


import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.input.EditFieldType
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.text_field.TextFieldUI
import com.do55anto5.quinto_code.presenter.components.top_app_bar.TopAppBarUI
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.OnGetUser
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.OnSave
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction.OnValueChange
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel.ProfileViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsState()
    val action = viewModel::submitAction

    ProfileContent(
        state = state,
        action = action,
        navigateBack = navigateBack
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateBack: () -> Unit
) {

    LaunchedEffect(state.isLoading) {
        if (state.isLoading) {
            action(OnGetUser)
        }
    }

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
                            Image(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(60.dp))
                                    .border(
                                        width = 2.dp,
                                        color = QuintoCodeTheme.colorScheme.defaultColor,
                                        shape = RoundedCornerShape(110.dp)
                                    )
                                    .size(120.dp),
                                painter = painterResource(id = R.drawable.ic_user_mock),
                                contentDescription = null
                            )
                            Icon(
                                modifier = Modifier
                                    .align(BottomEnd),
                                painter = painterResource(id = R.drawable.ic_camera_fill),
                                contentDescription = stringResource(R.string.icon_description_edit_label_profile_screen),
                                tint = QuintoCodeTheme.colorScheme.defaultColor
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    TextFieldUI(
                        value = state.name,
                        label =stringResource(R.string.text_field_label_name_profile_screen),
                        placeholder = state.name,
                        mLeadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_pencil_fill),
                                contentDescription = null,
                                tint = QuintoCodeTheme.colorScheme.greyscale500Color
                            )
                        },
                        enabled = true,
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
                        enabled = true,
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
                        enabled = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
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
                                painter = painterResource(R.drawable.ic_pencil_fill),
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
                        onClick = {
                            action(
                                OnSave
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
private fun ProfileScreenPreview() {
    ProfileScreen(
        navigateBack = {}
    )
}