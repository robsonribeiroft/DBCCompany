package com.rrdev.di

import com.rrdev.data.repository.EventRepositoryImpl
import com.rrdev.domain.repository.EventRepository
import org.koin.dsl.module

val dataModule = module {

    single<EventRepository> { EventRepositoryImpl(get()) }
}