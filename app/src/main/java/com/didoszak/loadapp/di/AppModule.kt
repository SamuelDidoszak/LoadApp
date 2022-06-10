package com.didoszak.loadapp.di

import android.app.Application
import androidx.room.Room
import com.didoszak.loadapp.feature_add_find_job.data.data_source.UserDatabase
import com.didoszak.loadapp.feature_add_find_job.data.repository.ApiRepositoryImpl
import com.didoszak.loadapp.feature_add_find_job.data.repository.UserRepositoryImpl
import com.didoszak.loadapp.feature_add_find_job.domain.repository.Api
import com.didoszak.loadapp.feature_add_find_job.domain.repository.ApiRepository
import com.didoszak.loadapp.feature_add_find_job.domain.repository.UserRepository
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.ApiUseCases
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.GetAllLanguages
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.GetAllQualifications
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.GetRoutes
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.DeleteUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.GetUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.InsertUser
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideLoadAppApi(): Api = Retrofit.Builder()
        .baseUrl("https://load-app-server.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    @Provides
    @Singleton
    fun provideApiRepository(api: Api): ApiRepository {
        return ApiRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideApiUseCases(repository: ApiRepository): ApiUseCases {
        return ApiUseCases(
            getAllLanguages = GetAllLanguages(repository),
            getAllQualifications = GetAllQualifications(repository),
            getRoutes = GetRoutes(repository)
        )
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