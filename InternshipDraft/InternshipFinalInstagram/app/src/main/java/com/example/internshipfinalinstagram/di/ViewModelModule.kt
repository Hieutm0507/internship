package com.example.internshipfinalinstagram.di

import com.example.internshipfinalinstagram.repositories.APIRepositoryImpl
import com.example.internshipfinalinstagram.viewmodels.AuthViewModel
import com.example.internshipfinalinstagram.viewmodels.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        APIRepositoryImpl()
    }

    viewModel {
        AuthViewModel(get())
    }

    viewModel {
        PostViewModel(get())
    }
}