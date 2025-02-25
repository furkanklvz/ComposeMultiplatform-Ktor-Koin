package org.klavs.demo.di

import org.klavs.demo.data.datasource.wciwservice.WCIWServiceDatasource
import org.klavs.demo.ktor.KtorClient
import org.klavs.demo.ktor.KtorService
import org.klavs.demo.data.datasource.wciwservice.WCIWServiceDatasourceImpl
import org.klavs.demo.data.repository.wciwservice.WCIWServiceRepository
import org.klavs.demo.data.repository.wciwservice.WCIWServiceRepositoryImpl
import org.klavs.demo.viewmodel.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { KtorClient.getClient() }
    singleOf(::KtorService)
    singleOf(::WCIWServiceDatasourceImpl){bind<WCIWServiceDatasource>()}
    singleOf(::WCIWServiceRepositoryImpl){bind<WCIWServiceRepository>()}
    viewModelOf(::HomeViewModel)
}