package com.do55anto5.quinto_code.presenter.screens.main.home.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.home.action.HomeAction
import com.do55anto5.quinto_code.presenter.screens.main.home.state.HomeState
import com.do55anto5.quinto_code.presenter.screens.main.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    action: (HomeAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    HomeContent(
        state = HomeState(),
        action = {}
    )
}