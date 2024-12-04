package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.presenter.screens.app.viewmodel.AppViewModel
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.viewmodel.ForgotPasswordViewModel
import com.do55anto5.quinto_code.presenter.screens.authentication.google_auth.viewmodel.GoogleSignInViewModel
import com.do55anto5.quinto_code.presenter.screens.authentication.login.viewmodel.LoginViewModel
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.viewmodel.SignupViewModel
import com.do55anto5.quinto_code.presenter.screens.main.bag.viewmodel.BagViewModel
import com.do55anto5.quinto_code.presenter.screens.main.favorite.viewmodel.FavoriteViewModel
import com.do55anto5.quinto_code.presenter.screens.main.home.viewmodel.HomeViewModel
import com.do55anto5.quinto_code.presenter.screens.main.hub.viewmodel.HubViewModel
import com.do55anto5.quinto_code.presenter.screens.main.notification.viewmodel.NotificationViewModel
import com.do55anto5.quinto_code.presenter.screens.main.profile.viewmodel.ProfileViewModel
import com.do55anto5.quinto_code.presenter.screens.main.search.viewmodel.SearchViewModel
import com.do55anto5.quinto_code.presenter.screens.main.store.viewmodel.MyStoreViewModel
import com.do55anto5.quinto_code.presenter.screens.splash.viewmodel.SplashViewModel
import com.do55anto5.quinto_code.presenter.screens.welcome.viewmodel.WelcomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {

    viewModel {
        SplashViewModel(
            appPreferences = get()
        )
    }

    viewModel {
        WelcomeViewModel(
            appPreferences = get()
        )
    }

    viewModel {
        SignupViewModel(
            registerUseCase = get()
        )
    }

    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }

    viewModel {
        ForgotPasswordViewModel(
            forgotPasswordUseCase = get()
        )
    }

    viewModel {
        GoogleSignInViewModel(
            useCase = get()
        )
    }

    viewModel {
        AppViewModel(
            getUserUseCase = get(),
            getProfilePhotoUseCase = get()
        )
    }

    viewModel {
        ProfileViewModel(
            saveUserUseCase = get(),
            getUserUseCase = get(),
            saveProfilePhotoUseCase = get(),
            getProfilePhotoUseCase = get()
        )
    }

    viewModel {
        HomeViewModel()
    }

    viewModel {
        SearchViewModel()
    }

    viewModel {
        BagViewModel()
    }

    viewModel {
        HubViewModel()
    }

    viewModel {
        FavoriteViewModel()
    }

    viewModel {
        NotificationViewModel()
    }

    viewModel {
        MyStoreViewModel()
    }

}