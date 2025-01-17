package com.shift.shiftfinal.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shift.shiftfinal.data.AuthorizationService
import com.shift.shiftfinal.data.api.AuthApi
import com.shift.shiftfinal.data.api.LoanApi
import com.shift.shiftfinal.data.converter.OffsetDateTimeConverter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.OffsetDateTime
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://shift-loan-app.exodar.heartlessguy.pro/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideLoanApi(retrofit: Retrofit): LoanApi = retrofit
        .create(LoanApi::class.java)


    private fun provideGson(): Gson = GsonBuilder().apply {
        registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeConverter)
    }.create()

    @Provides
    @Singleton
    fun provideAuthorizationService(context: Context) = AuthorizationService(context)

}