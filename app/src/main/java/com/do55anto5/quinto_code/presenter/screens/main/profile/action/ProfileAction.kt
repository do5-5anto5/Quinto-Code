package com.do55anto5.quinto_code.presenter.screens.main.profile.action

import android.content.Context
import android.net.Uri
import com.do55anto5.quinto_code.core.enums.input.EditFieldType


sealed class ProfileAction {

    data class OnValueChange(
        val value: String,
        val type: EditFieldType
    ) : ProfileAction()

    data object OnSave : ProfileAction()

    data object ResetErrorState: ProfileAction()

    data class OnImagePick(
        val context: Context,
        val currentImage: Uri?
    ): ProfileAction()
}