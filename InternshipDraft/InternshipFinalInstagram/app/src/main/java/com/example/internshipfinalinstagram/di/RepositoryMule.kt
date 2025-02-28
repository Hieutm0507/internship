package com.example.internshipfinalinstagram.di

import com.example.internshipfinalinstagram.apis.ApiClient
import com.example.internshipfinalinstagram.repositories.APIRepository
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiClient.getApi() }
    factory<APIRepository> { APIRepositoryImpl(get()) }
}