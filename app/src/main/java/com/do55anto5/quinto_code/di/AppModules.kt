package com.do55anto5.quinto_code.di

import org.koin.dsl.module

val appModules = module {
    includes(
        repositoryModule,
        useCaseModule,
        presenterModule,
        firebaseModule
    )
}