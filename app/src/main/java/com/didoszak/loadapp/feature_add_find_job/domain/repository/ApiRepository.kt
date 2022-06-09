package com.didoszak.loadapp.feature_add_find_job.domain.repository

import com.didoszak.loadapp.feature_add_find_job.data.model.Language
import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiRepository {
    suspend fun getLanguages(): Resource<List<Language>>

    suspend fun getQualifications(): Resource<List<Qualification>>

    suspend fun login()

    suspend fun createUser(user: User)
}