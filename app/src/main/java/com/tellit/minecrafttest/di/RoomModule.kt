package com.tellit.minecrafttest.di

import android.app.Application
import androidx.room.Room
import com.tellit.minecrafttest.data.AppDatabase
import com.tellit.minecrafttest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aslbek on 03.11.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext, AppDatabase::class.java, Constants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesCardDao(appDatabase: AppDatabase) = appDatabase.getCardDao()

}