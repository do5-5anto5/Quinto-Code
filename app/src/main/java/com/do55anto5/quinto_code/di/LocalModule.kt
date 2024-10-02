package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.core.preferences.AppPreferences
import org.koin.dsl.module

val localModule = module {
    single<AppPreferences> {
        AppPreferences(context = get())
    }
}