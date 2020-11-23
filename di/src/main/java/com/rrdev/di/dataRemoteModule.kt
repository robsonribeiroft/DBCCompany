package com.rrdev.di

import com.rrdev.data.datasource.EventRemoteDataSource
import com.rrdev.data_remote.datasource.EventRemoteDataSourceImpl
import com.rrdev.data_remote.util.RequestServiceWrapper
import com.rrdev.data_remote.util.RequestServiceWrapperImpl
import com.rrdev.data_remote.util.ServiceFactory
import org.koin.dsl.module

val dataRemoteModule = module {

    single { ServiceFactory.createService() }

    single<RequestServiceWrapper> { RequestServiceWrapperImpl() }

    single<EventRemoteDataSource> { EventRemoteDataSourceImpl(get(), get()) }
}