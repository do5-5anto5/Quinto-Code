package com.do55anto5.quinto_code.presenter.screens.main.search.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.search.action.SearchAction
import com.do55anto5.quinto_code.presenter.screens.main.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun submitAction(action: SearchAction) {

    }

}