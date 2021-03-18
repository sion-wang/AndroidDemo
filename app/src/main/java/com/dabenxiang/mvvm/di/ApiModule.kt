package com.dabenxiang.mvvm.di

import com.dabenxiang.mvvm.BuildConfig
import com.dabenxiang.mvvm.model.api.ApiRepository
import com.dabenxiang.mvvm.model.api.ApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideApiService(get()) }
    single { provideApiRepository(get()) }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
    }
    return httpLoggingInterceptor
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)

    if (BuildConfig.DEBUG) {
        builder.addNetworkInterceptor(StethoInterceptor())
    }

    return builder.build()
}

fun provideApiService(okHttpClient: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://api.github.com")
        .build()
        .create(ApiService::class.java)
}

fun provideApiRepository(apiService: ApiService): ApiRepository {
    return ApiRepository(apiService)
}
