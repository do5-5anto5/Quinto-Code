package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.data.remote.repository.authentication.SignupRepositoryImpl
import com.do55anto5.quinto_code.domain.remote.repository.authentication.SignupRepository
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository
import com.do55anto5.quinto_code.data.remote.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<SignupRepository> { SignupRepositoryImpl() }

    factory<UserRepository> { UserRepositoryImpl() }

}