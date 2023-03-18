package com.example.test.di

import com.example.test.data.PhotoPagingSource
import com.example.test.data.Repository
import com.example.test.data.RepositoryImpl
import com.example.test.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHTTPClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level= HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideRepo(
        retrofit: Retrofit,
        photoPagingSource: PhotoPagingSource
    ) : Repository = RepositoryImpl(retrofit, photoPagingSource)


    @Provides
    fun providePagingSource(retrofit: Retrofit) : PhotoPagingSource = PhotoPagingSource(retrofit)
}