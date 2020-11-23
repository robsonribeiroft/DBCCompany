package com.rrdev.di

import com.rrdev.presentation_event.EventDetailViewModel
import com.rrdev.presentation_event.EventListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { EventListViewModel(androidApplication(), get()) }
    viewModel { EventDetailViewModel(androidApplication(), get(), get()) }
}