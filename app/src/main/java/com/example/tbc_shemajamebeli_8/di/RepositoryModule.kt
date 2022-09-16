package com.example.tbc_shemajamebeli_8.di

import com.example.tbc_shemajamebeli_8.data.repository.ClothesRepositoryImpl
import com.example.tbc_shemajamebeli_8.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: ClothesRepositoryImpl
    ): MyRepository
}