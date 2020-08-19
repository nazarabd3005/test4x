package com.nazar.test4x.core.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nazar.test4x.core.BuildConfig
import com.nazar.test4x.core.database.AppDb
import com.nazar.test4x.core.pref.SharePref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideConnectionSpec():ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2)
        .cipherSuites(
            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
        .build();

    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner = CertificatePinner.Builder()
        .add("rickandmortyapi.com", "sha256/gGOcYKAwzEaUfun6YdxZvFSQq/x2lF/R8UizDFofveY=")
        .build()
    @Provides
    @Singleton
    fun provideOkHttpClient(
        connectionSpec: ConnectionSpec,
        certificatePinner: CertificatePinner
    ): OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(Collections.singletonList(connectionSpec))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }).build()

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()


    //db
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDb = AppDb.getDatabase(context)

    //pref
    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharePref = SharePref(context)

}

