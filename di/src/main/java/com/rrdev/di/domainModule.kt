package com.rrdev.di

import com.rrdev.domain.usecase.GetAllEvents
import com.rrdev.domain.usecase.GetEventDetail
import com.rrdev.domain.usecase.RegisterInEvent
import org.koin.dsl.module

val domainModule = module {

    single { GetAllEvents(get()) }

    single { GetEventDetail(get()) }

    single { RegisterInEvent(get()) }
}