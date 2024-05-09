package com.prathameshkumbhar.investify.di

import android.app.Application
import androidx.room.Room
import com.prathameshkumbhar.investify.data.local.database.InvestifyDatabase
import com.prathameshkumbhar.investify.data.remote.InvestifyApiService
import com.prathameshkumbhar.investify.util.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InvestifyApplicationModule {

    @Provides
    @Singleton
    fun provideInvestifyApiService() : InvestifyApiService{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideInvestifyDatabase(application: Application): InvestifyDatabase{
        return Room.databaseBuilder(
            application,
            InvestifyDatabase::class.java,
            "investify.db"
        ).build()
    }
}