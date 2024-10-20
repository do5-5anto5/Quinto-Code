package com.do55anto5.quinto_code.presenter.screens.app.screen


import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.MainActivity
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.helper.FirebaseHelper.Companion.logout
import com.do55anto5.quinto_code.core.navigation.drawer.DrawerItem
import com.do55anto5.quinto_code.presenter.components.navigation_drawer.NavigationDrawerQC
import com.do55anto5.quinto_code.presenter.screens.app.action.AppAction
import com.do55anto5.quinto_code.presenter.screens.app.state.AppState
import com.do55anto5.quinto_code.presenter.screens.app.viewmodel.AppViewModel
import com.do55anto5.quinto_code.presenter.screens.main.bag.screen.BagScreen
import com.do55anto5.quinto_code.presenter.screens.main.favorite.screen.FavoriteScreen
import com.do55anto5.quinto_code.presenter.screens.main.home.screen.HomeScreen
import com.do55anto5.quinto_code.presenter.screens.main.hub.screen.HubScreen
import com.do55anto5.quinto_code.presenter.screens.main.search.screen.SearchScreen
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScreen(
    navigateToProfileScreen: () -> Unit
) {

    val viewModel = koinViewModel<AppViewModel>()
    val appState by viewModel.state.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(!appState.isAuthenticated) {
        if (!appState.isAuthenticated) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    AppContent(
        state = appState,
        action = viewModel::submitAction,
        navigateToProfileScreen = navigateToProfileScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppContent(
    state: AppState,
    action: (AppAction) -> Unit,
    navigateToProfileScreen: () -> Unit
) {

    val drawerState = rememberDrawerState(state.drawerStateValue)
    val scope = rememberCoroutineScope()

    LaunchedEffect(drawerState.currentValue) {
        action(AppAction.DrawerStateChanged(drawerState.currentValue == DrawerValue.Open))
    }

    NavigationDrawerQC(
        drawerState = drawerState,
        drawerIndex = state.currentDrawerIndex,
        items = DrawerItem.items,
        onClick = {
            scope.launch {
                drawerState.close()
                action(AppAction.DrawerItemClicked(it))
            }
        },
        navigateToProfileScreen = {
            navigateToProfileScreen()
        },
        content = {
            Scaffold(
                modifier = Modifier,
                topBar = {
                    TopAppBar(
                        title = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Text(
                                        modifier = Modifier,
                                        text = stringResource(
                                            DrawerItem.items[state.currentDrawerIndex].title
                                        ),
                                        color = QuintoCodeTheme.colorScheme.defaultColor,
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    if (drawerState.isClosed) {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = null,
                                        tint = QuintoCodeTheme.colorScheme.defaultColor
                                    )
                                }
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    logout()
                                    action(AppAction.OnLogout)
                                },
                                content = {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_logout),
                                        contentDescription = null,
                                        tint = QuintoCodeTheme.colorScheme.defaultColor
                                    )
                                }
                            )
                        },
                        colors = topAppBarColors(
                            containerColor = QuintoCodeTheme.colorScheme.backgroundColor
                        )
                    )
                },
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(QuintoCodeTheme.colorScheme.backgroundColor)
                            .padding(paddingValues),
                        content = {
                            AnimatedContent(
                                targetState = state.currentDrawerIndex,
                                transitionSpec = {
                                    val direction =
                                        if (targetState > state.previousDrawerIndex) 1 else -1
                                    (slideInHorizontally { width -> direction * width } + fadeIn()).togetherWith(
                                        slideOutHorizontally { width -> -direction * width } + fadeOut())
                                },
                                modifier = Modifier.fillMaxSize(),
                                content = { currentIndex ->
                                    when (currentIndex) {
                                        0 -> HomeScreen()
                                        1 -> SearchScreen()
                                        2 -> BagScreen()
                                        3 -> HubScreen()
                                        4 -> FavoriteScreen()
                                    }
                                    BackHandler {
                                        action(AppAction.NavigateBack)
                                    }
                                },
                                label = ""
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun AppScreenPreview() {
    AppScreen(
        navigateToProfileScreen = {}
    )
}