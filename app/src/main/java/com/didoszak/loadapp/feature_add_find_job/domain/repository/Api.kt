package com.didoszak.loadapp.feature_add_find_job.domain.repository

import com.didoszak.loadapp.feature_add_find_job.data.model.Language
import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("/languages")
    suspend fun getLanguages(): Response<List<Language>>

    @GET("/qualifications")
    suspend fun getQualifications(): Response<List<Qualification>>

    @GET("/login")
    suspend fun login()

    @POST("/user")
    suspend fun createUser(@Body user: User)
}