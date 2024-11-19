package com.do55anto5.quinto_code.presenter.components.image


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ImageUI(
    modifier: Modifier = Modifier,
    imageModel: Any,
    previewPlaceholder: Painter? = null,
    contentScale: ContentScale = ContentScale.None,
    shape: Shape = RoundedCornerShape(16.dp),
    borderStroke: BorderStroke = BorderStroke(
        width = 0.dp,
        color = QuintoCodeTheme.colorScheme.transparentColor),
    onClick: () -> Unit = {},
    isLoading: Boolean
) {
    val loadingComposition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))
    val failureComposition by rememberLottieComposition(LottieCompositionSpec.Asset("error-animation.json"))

   CoilImage(
       imageModel = { imageModel },
       modifier = modifier
           .clip(shape = shape)
           .clickable { onClick() }
           .border(borderStroke, shape),
       imageOptions = ImageOptions(
           contentScale = contentScale,
           alignment = Alignment.Center
       ),
       previewPlaceholder = previewPlaceholder,
       loading = {
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .background(QuintoCodeTheme.colorScheme.backgroundColor)
           ) {
               LottieAnimation(
                   composition = loadingComposition,
                   modifier = Modifier
                       .align(Alignment.Center),
                   iterations = LottieConstants.IterateForever,
                   maintainOriginalImageBounds = true
               )
           }
       },
       failure = {
           if (!isLoading) {
               LottieAnimation(
                   composition = failureComposition,
                   modifier = Modifier
                       .align(Alignment.BottomCenter),
                   iterations = LottieConstants.IterateForever,
                   maintainOriginalImageBounds = true
               )
           }
       }
   )
}

@PreviewLightDark
@Composable
private fun ImageUIPreview() {
    QuintoCodeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QuintoCodeTheme.colorScheme.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageUI(
                modifier = Modifier
                    .size(200.dp),
                imageModel = "https://mbinaram-ia-generativa_188544-9656.jpg",
                contentScale = ContentScale.Crop,
                previewPlaceholder = painterResource(id = R.drawable.splash_logo),
                borderStroke = BorderStroke(2.dp, QuintoCodeTheme.colorScheme.defaultColor),
                onClick = {},
                isLoading = true
            )
        }
    }
}