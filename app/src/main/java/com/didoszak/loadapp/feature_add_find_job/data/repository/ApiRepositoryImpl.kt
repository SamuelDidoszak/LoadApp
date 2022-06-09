package com.didoszak.loadapp.feature_add_find_job.data.repository

import android.util.Log
import com.didoszak.loadapp.feature_add_find_job.data.model.Language
import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.repository.ApiRepository
import com.didoszak.loadapp.feature_add_find_job.domain.repository.Api
import com.didoszak.loadapp.feature_add_find_job.domain.util.Resource
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: Api
) : ApiRepository {
    override suspend fun getLanguages(): Resource<List<Language>> {
        return try {
            val response = api.getLanguages()
            val result = response.body()
            if(response.isSuccessful && result != null)
                Resource.Success(result)
            else
                Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getQualifications(): Resource<List<Qualification>> {
        return try {
            val response = api.getQualifications()
            val result = response.body()
            if(response.isSuccessful && result != null)
                Resource.Success(result)
            else
                Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun login() {
        api.login()
    }

    override suspend fun createUser(user: User) {
        api.createUser(user)
    }
}