package com.example.rappichallenge.dagger

import com.example.rappichallenge.NearbyRestaurantsAdapterFactory
import com.example.rappichallenge.repository.remote.AppService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class Providers {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().add(NearbyRestaurantsAdapterFactory()).add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun bindRetrofitService(moshi: Moshi): AppService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(AppService.BaseUrl)
            .build()
            .create(AppService::class.java)
    }
}