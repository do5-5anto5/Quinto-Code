package com.do55anto5.quinto_code.presenter.screens.main.profile.action

import com.do55anto5.quinto_code.core.enums.input.EditFieldType


sealed class ProfileAction {

    data class OnValueChange(
        val value: String,
        val type: EditFieldType
    ) : ProfileAction()

    data object OnSave : ProfileAction()

    data object OnGetUser: ProfileAction()

}