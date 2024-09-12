package com.do55anto5.quinto_code.presenter.splash.screen

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.do55anto5.quinto_code.MainActivity
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    SplashContent()
}

@Composable
fun SplashContent() {


    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // TODO: handle result
    }

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f,
            animationSpec = tween(durationMillis = 1500))
        delay(3000)
        val intent = Intent(context, MainActivity::class.java)
        launcher.launch(intent)
    }


    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = QuintoCodeTheme.colorScheme.backgroundColor,
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(vertical = 100.dp),
                content = {

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .alpha(alpha.value)
                            .clip(RoundedCornerShape(110.dp))
                            .align(Alignment.Center)
                            .border(
                                width = 2.dp,
                                color = QuintoCodeTheme.colorScheme.defaultColor,
                                shape = RoundedCornerShape(110.dp)
                            )
                            .size(240.dp),
                    )

                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .alpha(alpha.value)
                            .size(120.dp)
                            .align(Alignment.BottomCenter),
                        iterations = LottieConstants.IterateForever,
                        maintainOriginalImageBounds = true
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun SplashScreenView() {
    QuintoCodeTheme {
        SplashContent()
    }
}