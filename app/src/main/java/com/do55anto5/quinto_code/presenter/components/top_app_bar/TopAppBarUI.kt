package com.do55anto5.quinto_code.presenter.components.top_app_bar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(
    modifier: Modifier = Modifier,
    title: String = "",
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.8.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight.Bold,
                    color = QuintoCodeTheme.colorScheme.textColor
                )
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onClick,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_line),
                        contentDescription = null,

                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = QuintoCodeTheme.colorScheme.backgroundColor,
            navigationIconContentColor = QuintoCodeTheme.colorScheme.topAppBarColor,
            actionIconContentColor = QuintoCodeTheme.colorScheme.topAppBarColor,
            titleContentColor = QuintoCodeTheme.colorScheme.topAppBarColor,
        )
    )
}

@PreviewLightDark
@Composable
private fun TopAppBarUIPreview() {
    QuintoCodeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(QuintoCodeTheme.colorScheme.backgroundColor)
        ) {
            TopAppBarUI(
                title = "mock string",
                onClick = {}
            )
        }
    }
}