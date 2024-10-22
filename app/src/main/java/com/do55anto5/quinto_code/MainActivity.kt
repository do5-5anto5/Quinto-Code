package com.do55anto5.quinto_code

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.do55anto5.quinto_code.core.navigation.hosts.onboarding.OnboardingNavHost
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(QuintoCodeTheme.colorScheme.backgroundColor),
                content = {
                    QuintoCodeTheme {
                        OnboardingNavHost(navHostController = rememberNavController())
                    }
                }
            )
        }
    }
}