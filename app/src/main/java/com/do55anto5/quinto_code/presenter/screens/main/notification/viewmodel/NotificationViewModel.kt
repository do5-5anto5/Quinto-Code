package com.do55anto5.quinto_code.presenter.screens.main.notification.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.notification.action.NotificationAction
import com.do55anto5.quinto_code.presenter.screens.main.notification.state.NotificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {

    private val _state = MutableStateFlow(NotificationState())
    val state = _state.asStateFlow()

    fun submitAction(action: NotificationAction) {

    }

}