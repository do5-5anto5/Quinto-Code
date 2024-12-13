package com.do55anto5.quinto_code.presenter.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("secondary-loading.json"))

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .shadow(
                shape = RoundedCornerShape(100.dp),
                elevation = 4.dp,
                spotColor = QuintoCodeTheme.colorScheme.defaultColor,
                ambientColor = QuintoCodeTheme.colorScheme.defaultColor
            )
        ,
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = QuintoCodeTheme.colorScheme.defaultColor,
            disabledContainerColor = QuintoCodeTheme.colorScheme.disabledDefaultColor
        ),
        content = {
            if (isLoading) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(70.dp),
                    iterations = LottieConstants.IterateForever,
                    maintainOriginalImageBounds = true,
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight.Bold,
                        color = QuintoCodeTheme.colorScheme.whiteColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun PrimaryButtonPreview() {
    QuintoCodeTheme() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QuintoCodeTheme.colorScheme.backgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                text = "Continue",
                isLoading = false,
                enabled = true,
                onClick = {}
            )
        }
    }
}