package com.do55anto5.quinto_code.presenter.screens.main.store.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction
import com.do55anto5.quinto_code.presenter.screens.main.store.state.MyStoreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyStoreViewModel : ViewModel() {

    private val _state = MutableStateFlow(MyStoreState())
    val state = _state.asStateFlow()

    fun submitAction(action: MyStoreAction) {

    }
}