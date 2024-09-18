package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.data.remote.repository.authentication.SignupRepositoryImpl
import com.do55anto5.quinto_code.domain.remote.repository.authentication.SignupRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SignupRepository> { SignupRepositoryImpl(auth = get()) }

}