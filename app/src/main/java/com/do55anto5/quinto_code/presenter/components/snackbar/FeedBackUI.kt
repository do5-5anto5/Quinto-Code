package com.do55anto5.quinto_code.presenter.components.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType.ERROR
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType.INFO
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType.SUCCESS
import com.do55anto5.quinto_code.core.enums.feedback.FeedbackType.WARNING
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@Composable
fun FeedbackUI(
    modifier: Modifier = Modifier,
    message: String,
    type: FeedbackType
) {

    val defaultColor = when (type) {
        SUCCESS -> QuintoCodeTheme.colorScheme.successColor
        INFO -> QuintoCodeTheme.colorScheme.infoColor
        WARNING -> QuintoCodeTheme.colorScheme.warningColor
        ERROR -> QuintoCodeTheme.colorScheme.errorColor
    }

    val textColor = when(type) {
        SUCCESS -> QuintoCodeTheme.colorScheme.successTextColor
        INFO -> QuintoCodeTheme.colorScheme.infoTextColor
        WARNING -> QuintoCodeTheme.colorScheme.warningTextColor
        ERROR -> QuintoCodeTheme.colorScheme.errorTextColor
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = defaultColor.copy(alpha = 0.7f)
        ),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = {

                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = null,
                        tint = defaultColor
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(text = message,
                        style = TextStyle(
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = textColor,
                            letterSpacing = 0.5.sp
                        ))
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun FeedbackUIPreview() {
    QuintoCodeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QuintoCodeTheme.colorScheme.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {

                FeedbackUI(
                    message = "Login successful",
                    type = SUCCESS
                )

                FeedbackUI(
                    message = "Info",
                    type = INFO
                )

                FeedbackUI(
                    message = "Warning",
                    type =WARNING

                )
                FeedbackUI(
                    message = "Error",
                    type = ERROR
                )
            }
        )
    }
}