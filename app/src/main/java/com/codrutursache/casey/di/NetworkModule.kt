package com.codrutursache.casey.di

import com.codrutursache.casey.BuildConfig
import com.codrutursache.casey.data.data_source.SpoonacularService
import com.codrutursache.casey.Constants.LOGGING_INTERCEPTOR_TAG
import com.codrutursache.casey.Constants.SPOONACULAR_API_KEY_INTERCEPTOR_TAG
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @Named(SPOONACULAR_API_KEY_INTERCEPTOR_TAG)
    fun provideApiKeyInterceptor(): Interceptor = Interceptor {
        val url = it
            .request()
            .url
            .newBuilder()
            .build()

        val request = it
            .request()
            .newBuilder()
            .url(url)
            .addHeader("X-RapidAPI-Key", BuildConfig.RAPID_API_KEY)
            .addHeader("X-RapidAPI-Host", SpoonacularService.RAPID_API_HOST)
            .build()

        it.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(SPOONACULAR_API_KEY_INTERCEPTOR_TAG)
        apiKeyInterceptor: Interceptor,

        @Named(LOGGING_INTERCEPTOR_TAG)
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
//            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideSpoonacularRetrovitInstance(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(SpoonacularService.RAPID_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()


    @Provides
    @Singleton
    fun provideSpoonacularApi(retrofit: Retrofit): SpoonacularService =
        retrofit.create(SpoonacularService::class.java)
}