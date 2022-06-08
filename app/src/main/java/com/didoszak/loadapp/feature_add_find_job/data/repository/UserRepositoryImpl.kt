package com.didoszak.loadapp.feature_add_find_job.data.repository

import com.didoszak.loadapp.feature_add_find_job.data.data_source.UserDao
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override suspend fun getUserById(id: Int): User? {
        TODO("Add api handling")
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        TODO("Add api handling")
        return insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        return deleteUser(user)
    }
}