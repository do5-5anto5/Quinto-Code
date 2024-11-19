package com.do55anto5.quinto_code.presenter.screens.app.state

import androidx.compose.material3.DrawerValue
import com.do55anto5.quinto_code.domain.remote.model.User

data class AppState(
    val user: User = User(),
    val currentDrawerIndex: Int = 0,
    val previousDrawerIndex: Int = 0,
    val drawerStateValue: DrawerValue = DrawerValue.Closed,
    val isAuthenticated: Boolean = true,
    val isLoading: Boolean = true
)