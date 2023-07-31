package com.mengistu.transport_for_london.di

import com.mengistu.transport_for_london.common.Constants
import com.mengistu.transport_for_london.data.remote.TFLUAPI
import com.mengistu.transport_for_london.data.repository.Repository
import com.mengistu.transport_for_london.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideOkHttpInstance(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }


    @Provides
    fun provideRetrofitInstance(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideTFLUAPI(
        retrofit: Retrofit
    ): TFLUAPI {
        return retrofit.create(TFLUAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(tfluapi: TFLUAPI) : Repository = RepositoryImpl(tfluapi)
}