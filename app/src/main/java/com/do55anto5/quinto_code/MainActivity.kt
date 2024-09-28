package com.do55anto5.quinto_code

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.do55anto5.quinto_code.core.navigation.hosts.onboarding.OnboardingNavHost
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuintoCodeTheme {
                OnboardingNavHost(navHostController = rememberNavController())
            }
        }
    }
}