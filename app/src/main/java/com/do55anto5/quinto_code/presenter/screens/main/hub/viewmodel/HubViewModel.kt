package com.do55anto5.quinto_code.presenter.screens.main.hub.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.hub.action.HubAction
import com.do55anto5.quinto_code.presenter.screens.main.hub.state.HubState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HubViewModel : ViewModel() {

    private val _state = MutableStateFlow(HubState())
    val state = _state.asStateFlow()

    fun submitAction(action: HubAction) {

    }

}