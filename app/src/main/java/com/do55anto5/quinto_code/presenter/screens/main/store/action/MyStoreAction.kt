package com.do55anto5.quinto_code.presenter.screens.main.store.action

import android.content.Context
import android.net.Uri
import com.do55anto5.quinto_code.core.enums.input.CreateStoreFieldType


sealed class MyStoreAction {
    data class OnValueChange(
        val value: String,
        val type: CreateStoreFieldType
    ) : MyStoreAction()

    data object OnSave : MyStoreAction()

    data object ResetErrorState : MyStoreAction()

    data class OnImagePick(
        val context: Context,
        val currentImage: Uri?
    ) : MyStoreAction()
}