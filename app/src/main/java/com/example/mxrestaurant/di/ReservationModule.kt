package com.example.mxrestaurant.di

import com.example.mxrestaurant.data.repository.ReservationRepositoryImpl
import com.example.mxrestaurant.domain.repository.ReservationRepository
import com.example.mxrestaurant.domain.usecase.visitor.CreateReservationUseCase
import com.example.mxrestaurant.domain.usecase.visitor.GetAvailableTablesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReservationModule {

    @Provides
    @Singleton
    fun provideGetAvailableTablesUseCase(
        repository: ReservationRepository
    ): GetAvailableTablesUseCase {
        return GetAvailableTablesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCreateReservationUseCase(
        repository: ReservationRepository
    ): CreateReservationUseCase {
        return CreateReservationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideReservationRepository(): ReservationRepository {
        return ReservationRepositoryImpl()
    }
}
