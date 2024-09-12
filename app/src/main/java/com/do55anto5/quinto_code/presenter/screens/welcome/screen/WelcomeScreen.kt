package com.do55anto5.quinto_code.presenter.screens.welcome.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.WelcomeSliderUI
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun WelcomeScreen() {
    WelcomeContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeContent() {

    val slideItems = listOf(
        Pair(
            stringResource(id = R.string.welcome_slider_title),
            stringResource(id = R.string.welcome_slider_slide1)
        ),
        Pair(
            stringResource(id = R.string.welcome_slider_title),
            stringResource(id = R.string.welcome_slider_slide2)
        ),
        Pair(
            stringResource(id = R.string.welcome_slider_title),
            stringResource(id = R.string.welcome_slider_slide3)
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    Scaffold (
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

                    WelcomeSliderUI(
                        slideItems = slideItems,
                        pagerState = pagerState
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun WelcomeScreenPreview() {
    WelcomeContent()
}