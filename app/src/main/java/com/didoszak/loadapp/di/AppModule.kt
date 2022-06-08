package com.didoszak.loadapp.di

import android.app.Application
import androidx.room.Room
import com.didoszak.loadapp.feature_add_find_job.data.data_source.UserDatabase
import com.didoszak.loadapp.feature_add_find_job.data.repository.UserRepositoryImpl
import com.didoszak.loadapp.feature_add_find_job.domain.repository.UserRepository
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.DeleteUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.GetUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.InsertUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repository),
            insertUser = InsertUser(repository),
            deleteUser = DeleteUser(repository)
        )
    }
}