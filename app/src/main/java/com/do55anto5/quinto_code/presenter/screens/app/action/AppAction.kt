package com.do55anto5.quinto_code.presenter.screens.app.action

sealed class AppAction {

    data class DrawerItemClicked(val index: Int) : AppAction()
    data class DrawerStateChanged(val isOpen: Boolean) : AppAction()
    data object NavigateBack : AppAction()

}