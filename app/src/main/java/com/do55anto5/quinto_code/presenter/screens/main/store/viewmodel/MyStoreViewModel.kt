package com.do55anto5.quinto_code.presenter.screens.main.store.viewmodel


import androidx.lifecycle.ViewModel
import com.do55anto5.quinto_code.core.enums.input.CreateStoreFieldType
import com.do55anto5.quinto_code.core.enums.input.CreateStoreFieldType.*
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction
import com.do55anto5.quinto_code.presenter.screens.main.store.action.MyStoreAction.*
import com.do55anto5.quinto_code.presenter.screens.main.store.state.MyStoreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyStoreViewModel : ViewModel() {

    private val _state = MutableStateFlow(MyStoreState())
    val state = _state.asStateFlow()

    fun submitAction(action: MyStoreAction) {
        when (action) {
            is OnValueChange -> {
                onValueChange(
                    type = action.type,
                    value = action.value
                )
            }
            is OnImagePick -> TODO()
            is OnSave -> TODO()

            is ResetErrorState -> TODO()
        }
    }

    private fun onValueChange(type: CreateStoreFieldType, value: String) {
        when (type) {
            NAME -> {
                changeName(value)
            }

            DESCRIPTION -> {
                changeDescription(value)
            }

            CITY -> {
                changeCity(value)
            }

            ADDRESS -> {
                changeAddress(value)
            }

            PHONE_NUMBER -> {
                changePhoneNumber(value)
            }
        }
    }

    private fun changeName(value: String) {
        _state.update { currentState ->
            currentState.copy(
                name = value
            )
        }
    }

    private fun changeDescription(value: String) {
        _state.update { currentState ->
            currentState.copy(
                description = value
            )
        }
    }

    private fun changeCity(value: String) {
        _state.update { currentState ->
            currentState.copy(
                city = value
            )
        }
    }

    private fun changeAddress(value: String) {
        _state.update { currentState ->
            currentState.copy(
                address = value
            )
        }
    }

    private fun changePhoneNumber(value: String) {
        _state.update { currentState ->
            currentState.copy(
                phoneNumber = value
            )
        }
    }


}