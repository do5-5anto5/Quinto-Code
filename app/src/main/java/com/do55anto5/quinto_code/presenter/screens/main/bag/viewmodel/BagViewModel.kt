package com.do55anto5.quinto_code.presenter.screens.main.bag.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.bag.action.BagAction
import com.do55anto5.quinto_code.presenter.screens.main.bag.state.BagState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BagViewModel : ViewModel() {

    private val _state = MutableStateFlow(BagState())
    val state = _state.asStateFlow()

    fun submitAction(action: BagAction) {

    }

}