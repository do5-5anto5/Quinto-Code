package com.do55anto5.quinto_code.presenter.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeSliderUI(
    modifier: Modifier = Modifier,
    slideItems: List<Pair<String, String>>,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        Modifier
            .fillMaxSize(),
        pageContent = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                content = {
                    Text(
                        text = slideItems[pagerState.currentPage].first,
                        style = TextStyle(
                            fontSize = 40.sp,
                            lineHeight = 48.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            color = QuintoCodeTheme.colorScheme.whiteColor,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = slideItems[pagerState.currentPage].second,
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 26.2.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Medium,
                            color = QuintoCodeTheme.colorScheme.whiteColor,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp
                        )
                    )
                }
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewLightDark
@Composable
private fun WelcomeSliderUIPreview() {
    val slideItems = listOf(
        Pair(
            stringResource(id = R.string.welcome_slider_title),
            stringResource(id = R.string.welcome_slider_slide1)),
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

    WelcomeSliderUI(
        slideItems = slideItems,
        pagerState = pagerState
    )
}