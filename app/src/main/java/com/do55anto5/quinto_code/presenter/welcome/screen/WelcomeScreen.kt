package com.do55anto5.quinto_code.presenter.welcome.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun WelcomeScreen() {
    WelcomeContent()
}

@Composable
fun WelcomeContent() {
    Box(modifier = Modifier
        .fillMaxSize()
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
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeContent()
}