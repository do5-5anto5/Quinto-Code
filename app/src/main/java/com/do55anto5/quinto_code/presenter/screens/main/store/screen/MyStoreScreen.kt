package com.do55anto5.quinto_code.presenter.screens.main.store.screen


import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction
import com.do55anto5.quinto_code.presenter.screens.main.store.state.MyStoreState
import com.do55anto5.quinto_code.presenter.screens.main.store.viewmodel.MyStoreViewModel

@Composable
fun MyStoreScreen() {

    val viewModel = koinViewModel<MyStoreViewModel>()
    val state by viewModel.state.collectAsState()

    MyStoreScreenContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun MyStoreScreenContent(
    state: MyStoreState,
    action: (MyStoreAction) -> Unit
) {

}

@PreviewLightDark
@Composable
private fun MyStoreScreenPreview() {
    MyStoreScreenContent(
        state = MyStoreState(),
        action = {}
    )
}