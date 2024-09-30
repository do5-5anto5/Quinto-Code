package com.do55anto5.quinto_code.presenter.screens.main.profile.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen() {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsState()

    ProfileContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun ProfileScreenPreview() {
    ProfileContent(
        state = ProfileState(),
        action = {}
    )
}