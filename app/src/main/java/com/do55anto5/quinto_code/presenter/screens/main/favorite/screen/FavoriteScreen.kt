package com.do55anto5.quinto_code.presenter.screens.main.favorite.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.favorite.action.FavoriteAction
import com.do55anto5.quinto_code.presenter.screens.main.favorite.state.FavoriteState
import com.do55anto5.quinto_code.presenter.screens.main.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen() {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val state by viewModel.state.collectAsState()

    FavoriteContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun FavoriteContent(
    state: FavoriteState,
    action: (FavoriteAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun FavoriteScreenPreview() {
    FavoriteContent(
        state = FavoriteState(),
        action = {}
    )
}