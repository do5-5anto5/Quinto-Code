package com.do55anto5.quinto_code.presenter.components.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun HorizontalDividerWithText(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f),
                color = QuintoCodeTheme.colorScheme.dividerColor
            )

            Text(
                text = stringResource(id = R.string.label_or_authentication_screen),
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 25.2.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = QuintoCodeTheme.colorScheme.textColor,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp
                )
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f),
                color = QuintoCodeTheme.colorScheme.dividerColor
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun HorizontalDividerWithTextPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDividerWithText(
            text = "ou"
        )
    }
}