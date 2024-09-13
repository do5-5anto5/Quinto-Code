package com.do55anto5.quinto_code.presenter.components.welcome_slider


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun SlideIndicatorUI(
    modifier: Modifier = Modifier,
    totalIndicators: Int,
    currentIndicator: Int
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        content = {
            repeat(totalIndicators) { index ->
                if (index == currentIndicator) {
                    Spacer(
                        modifier = Modifier
                            .width(32.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(QuintoCodeTheme.colorScheme.defaultColor)
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(QuintoCodeTheme.colorScheme.whiteColor)
                    )
                }

                if (index != totalIndicators - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun SlideIndicatorUIPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            SlideIndicatorUI(totalIndicators = 3, currentIndicator = 0)
        }
    )
}