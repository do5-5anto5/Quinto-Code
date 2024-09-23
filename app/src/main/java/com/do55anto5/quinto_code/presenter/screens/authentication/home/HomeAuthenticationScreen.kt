package com.do55anto5.quinto_code.presenter.screens.authentication.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.components.button.SocialButton
import com.do55anto5.quinto_code.presenter.components.divider.HorizontalDividerWithText
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme
import com.do55anto5.quinto_code.presenter.theme.UrbanistFamily

@Composable
fun HomeAuthenticationScreen() {
    HomeAuthenticationContent()
}

@Composable
fun HomeAuthenticationContent() {
    Scaffold(
        containerColor = QuintoCodeTheme.colorScheme.backgroundColor,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(110.dp))
                            .border(
                                width = 2.dp,
                                color = QuintoCodeTheme.colorScheme.defaultColor,
                                shape = RoundedCornerShape(110.dp)
                            )
                            .size(220.dp),
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = stringResource(id = R.string.label_title_authentication_screen),
                        style = TextStyle(
                            fontSize = 48.sp,
                            lineHeight = 57.6.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Bold,
                            color = QuintoCodeTheme.colorScheme.textColor,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SocialButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        icon = painterResource(id = R.drawable.ic_google),
                        isLoading = false,
                        text = stringResource(R.string.label_sign_with_google_authentication_screen),
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDividerWithText(
                        text = stringResource(id = R.string.label_or_authentication_screen)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.label_sign_with_password_authentication_screen),
                        isLoading = false,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            Text(
                                text = stringResource(id = R.string.label_sign_up_account_authentication_screen),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = UrbanistFamily,
                                    color = QuintoCodeTheme.colorScheme.textColor,
                                    textAlign = TextAlign.Right,
                                    letterSpacing = 0.2.sp
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                modifier = Modifier
                                    .clickable {  },
                                text = stringResource(id = R.string.label_sign_up_authentication_screen),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = QuintoCodeTheme.colorScheme.defaultColor,
                                    textAlign = TextAlign.Right,
                                    letterSpacing = 0.2.sp
                                )
                            )
                        }
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun HomeAuthenticationScreenPreview() {
    QuintoCodeTheme {
        HomeAuthenticationScreen()
    }
}