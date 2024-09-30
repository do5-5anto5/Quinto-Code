package com.do55anto5.quinto_code.presenter.screens.main.favorite.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.presenter.screens.main.favorite.action.FavoriteAction
import com.do55anto5.quinto_code.presenter.screens.main.favorite.state.FavoriteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun submitAction(action: FavoriteAction) {

    }

}