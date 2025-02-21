package com.example.internshipfinalinstagram.di

import com.example.internshipfinalinstagram.repositories.APIRepository
import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<APIRepository> { APIRepositoryImpl() }
}