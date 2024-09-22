package com.do55anto5.quinto_code.di

import com.do55anto5.quinto_code.data.remote.repository.authentication.AuthenticationRepositoryImpl
import com.do55anto5.quinto_code.domain.remote.repository.authentication.AuthenticationRepository
import com.do55anto5.quinto_code.domain.remote.repository.user.UserRepository
import com.do55anto5.quinto_code.data.remote.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<AuthenticationRepository> { AuthenticationRepositoryImpl() }

    factory<UserRepository> { UserRepositoryImpl() }

}