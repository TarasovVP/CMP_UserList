package com.tarasovvp.cmpuserlist.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.tarasovvp.cmpuserlist.data.database.SharedDatabase
import com.tarasovvp.cmpuserlist.data.database.datasource.LocalUserDataSource
import com.tarasovvp.cmpuserlist.data.database.datasource.LocalUserDataSourceImpl
import com.tarasovvp.cmpuserlist.data.network.datasource.RemoteUserDataSource
import com.tarasovvp.cmpuserlist.data.network.datasource.RemoteUserDataSourceImpl
import com.tarasovvp.cmpuserlist.data.repository.UserRepositoryImpl
import com.tarasovvp.cmpuserlist.domain.repository.UserRepository
import com.tarasovvp.cmpuserlist.domain.usecase.GetUserListUseCase
import com.tarasovvp.cmpuserlist.domain.usecase.GetUserListUseCaseImpl
import com.tarasovvp.cmpuserlist.presentation.UsersViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    single<RemoteUserDataSource> { RemoteUserDataSourceImpl(get()) }
    single {
        SharedDatabase(get())
    }
    single<LocalUserDataSource> { LocalUserDataSourceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<GetUserListUseCase> { GetUserListUseCaseImpl(get()) }
    viewModel { UsersViewModel() }
}