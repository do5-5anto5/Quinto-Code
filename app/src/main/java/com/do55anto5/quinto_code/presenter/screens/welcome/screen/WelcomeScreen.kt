package com.do55anto5.quinto_code.presenter.screens.welcome.screen


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.welcome_slider.WelcomeSliderUI
import com.do55anto5.quinto_code.presenter.screens.welcome.action.WelcomeAction
import com.do55anto5.quinto_code.presenter.screens.welcome.viewmodel.WelcomeViewModel
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun WelcomeScreen(
    navigateToHomeAuthenticationScreen: () -> Unit
) {

    val viewModel = koinViewModel<WelcomeViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.nextScreen) {
        if (state.nextScreen) {
            navigateToHomeAuthenticationScreen()
        }
    }

    WelcomeContent(
        action = viewModel::submitAction
    )
}

@Composable
fun WelcomeContent(
    action: (WelcomeAction) -> Unit
) {

    val slideItems = listOf(
        Pair(
            stringResource(id = R.string.welcome_slider_title1),
            stringResource(id = R.string.welcome_slider_slide1)
        ),
        Pair(
            stringResource(id = R.string.welcome_slider_title2),
            stringResource(id = R.string.welcome_slider_slide2)
        ),
        Pair(
            stringResource(id = R.string.welcome_slider_title3),
            stringResource(id = R.string.welcome_slider_slide3)
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
                .background(QuintoCodeTheme.colorScheme.backgroundColor),
                content = {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.bg_onboarding),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = R.drawable.background_gradient),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        content = {
                            WelcomeSliderUI(
                                modifier = Modifier
                                    .weight(1f),
                                slideItems = slideItems,
                                pagerState = pagerState
                            )

                            PrimaryButton(
                                modifier = Modifier
                                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                                text = stringResource(id = R.string.button_title_welcome_screen),
                                onClick = { action(WelcomeAction.OnNextScreen) }
                            )
                        }
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun WelcomeScreenPreview() {
    WelcomeContent( action = {} )
}