package com.example.tbc_shemajamebeli_8.di

import android.content.Context
import com.example.tbc_shemajamebeli_8.app.BaseApplication
import com.example.tbc_shemajamebeli_8.data.remote.Client
import com.example.tbc_shemajamebeli_8.data.remote.ClothesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(Client.getInstance(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): ClothesApi{
        return retrofit.create(ClothesApi::class.java)
    }

}