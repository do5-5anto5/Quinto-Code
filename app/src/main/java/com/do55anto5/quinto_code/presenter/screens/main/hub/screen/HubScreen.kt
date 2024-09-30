package com.do55anto5.quinto_code.presenter.screens.main.hub.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.hub.action.HubAction
import com.do55anto5.quinto_code.presenter.screens.main.hub.state.HubState
import com.do55anto5.quinto_code.presenter.screens.main.hub.viewmodel.HubViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HubScreen() {
    val viewModel = koinViewModel<HubViewModel>()
    val state by viewModel.state.collectAsState()

    HubContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun HubContent(
    state: HubState,
    action: (HubAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun HubScreenPreview() {
    HubContent(
    state = HubState(),
    action = {}
    )
}