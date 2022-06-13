package com.spacemooncake.iteractivetap.di

import com.spacemooncake.iteractivetap.data.repositopy.Repository
import com.spacemooncake.iteractivetap.data.repositopy.RepositoryImpl
import com.spacemooncake.iteractivetap.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
    single<Repository> { RepositoryImpl() }
}