package com.do55anto5.quinto_code.presenter.splash.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.do55anto5.quinto_code.MainActivity
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.splash.screen.SplashScreen
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            QuintoCodeTheme {
                SplashScreen()
            }
        }
    }

    @PreviewLightDark
    @Composable
    private fun TstSplashScreen() {

        val alpha = remember {
            Animatable(0f)
        }

        LaunchedEffect(key1 = true) {
            alpha.animateTo(1f,
                animationSpec = tween(durationMillis = 1500))
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = QuintoCodeTheme.colorScheme.backgroundColor
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .alpha(alpha.value),
                    painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = null
                )
            }
        }
    }
}

