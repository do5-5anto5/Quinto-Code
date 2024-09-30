package com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.profile.action.ProfileAction
import com.do55anto5.quinto_code.presenter.screens.main.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun submitAction(action: ProfileAction) {

    }

}