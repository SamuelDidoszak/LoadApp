package com.didoszak.loadapp.feature_add_find_job.domain.repository

import com.didoszak.loadapp.feature_add_find_job.domain.model.User

interface UserRepository {
    suspend fun getUserById(id: Int): User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)
}