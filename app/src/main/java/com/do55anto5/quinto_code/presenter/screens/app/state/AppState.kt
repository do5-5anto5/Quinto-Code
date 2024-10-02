package com.do55anto5.quinto_code.presenter.screens.app.state

import androidx.compose.material3.DrawerValue

data class AppState(
    val currentDrawerIndex: Int = 0,
    val previousDrawerIndex: Int = 0,
    val drawerStateValue: DrawerValue = DrawerValue.Closed
)