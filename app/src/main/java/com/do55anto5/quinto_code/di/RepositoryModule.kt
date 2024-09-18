package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.data.remote.SignupRepositoryImpl
import com.do55anto5.quinto_code.domain.remote.SignupRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SignupRepository> { SignupRepositoryImpl() }

}