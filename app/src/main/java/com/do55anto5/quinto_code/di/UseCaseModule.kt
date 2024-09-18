package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.domain.remote.usecase.authentication.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { RegisterUseCase(repository = get()) }

}