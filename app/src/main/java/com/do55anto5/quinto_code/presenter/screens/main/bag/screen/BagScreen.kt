package com.do55anto5.quinto_code.presenter.screens.main.bag.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.bag.action.BagAction
import com.do55anto5.quinto_code.presenter.screens.main.bag.state.BagState
import com.do55anto5.quinto_code.presenter.screens.main.bag.viewmodel.BagViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BagScreen() {
    val viewModel = koinViewModel<BagViewModel>()
    val state by viewModel.state.collectAsState()

    BagContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun BagContent(
    state: BagState,
    action: (BagAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun BagScreenPreview() {
    BagContent(
        state = BagState(),
        action = {}
    )
}