package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.domain.remote.usecase.authentication.ForgotPasswordUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.GoogleSignInUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.LoginUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.authentication.RegisterUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.image.GetProfilePhotoUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.image.SaveProfilePhotoUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.GetUserUseCase
import com.do55anto5.quinto_code.domain.remote.usecase.user.SaveUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { RegisterUseCase(repository = get()) }

    factory { SaveUserUseCase(repository = get()) }

    factory { GetUserUseCase(repository = get()) }

    factory { LoginUseCase(repository = get()) }

    factory { GoogleSignInUseCase(repository = get()) }

    factory { ForgotPasswordUseCase(repository = get()) }

    factory { SaveProfilePhotoUseCase(repository = get()) }

    factory { GetProfilePhotoUseCase(repository = get()) }

}