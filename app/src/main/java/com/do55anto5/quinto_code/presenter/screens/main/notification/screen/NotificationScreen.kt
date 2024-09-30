package com.do55anto5.quinto_code.presenter.screens.main.notification.screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.presenter.screens.main.notification.action.NotificationAction
import com.do55anto5.quinto_code.presenter.screens.main.notification.state.NotificationState
import com.do55anto5.quinto_code.presenter.screens.main.notification.viewmodel.NotificationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationScreen() {
    val viewModel = koinViewModel<NotificationViewModel>()
    val state by viewModel.state.collectAsState()

    NotificationContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun NotificationContent(
    state: NotificationState,
    action: (NotificationAction) -> Unit
) {
}

@PreviewLightDark
@Composable
private fun NotificationScreenPreview() {
    NotificationContent(
        state = NotificationState(),
        action = {}
    )
}