package com.do55anto5.quinto_code.presenter.screens.main.home.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.home.action.HomeAction
import com.do55anto5.quinto_code.presenter.screens.main.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun submitAction(action: HomeAction) {

    }

}