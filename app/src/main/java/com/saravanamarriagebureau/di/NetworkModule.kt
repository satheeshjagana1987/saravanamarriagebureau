package com.saravanamarriagebureau.di

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.util.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://lesser-staining-racoon.ngrok-free.dev/api/v1/"

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        appPreferences: AppPreferences
    ): AuthInterceptor {
        return AuthInterceptor(appPreferences)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // ---------------------------------------------------------------------
    // Refresh pipeline: a bare client + API used ONLY to call auth/refresh.
    // It has NO auth interceptor and NO authenticator, so a 401 on the refresh
    // endpoint cannot loop back into the TokenAuthenticator.
    // ---------------------------------------------------------------------

    @Provides
    @Singleton
    @RefreshApi
    fun provideRefreshOkHttp(
        logging: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    @RefreshApi
    fun provideRefreshApi(
        @RefreshApi client: OkHttpClient
    ): SmbApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmbApi::class.java)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        appPreferences: AppPreferences,
        @RefreshApi refreshApi: Provider<SmbApi>,
        sessionExpiredHandler: SessionExpiredHandler
    ): TokenAuthenticator =
        TokenAuthenticator(appPreferences, refreshApi, sessionExpiredHandler)

    // ---------------------------------------------------------------------
    // Main pipeline: auth interceptor adds the token, authenticator refreshes
    // it on 401.
    // ---------------------------------------------------------------------

    @Provides
    @Singleton
    fun provideOkHttp(
        logging: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): SmbApi =
        retrofit.create(SmbApi::class.java)
}
