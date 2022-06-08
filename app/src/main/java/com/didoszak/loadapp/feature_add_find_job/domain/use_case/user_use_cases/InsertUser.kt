package com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases

import com.didoszak.loadapp.feature_add_find_job.domain.model.InvalidUserException
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.repository.UserRepository

class InsertUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {

        if(user.email.isBlank())
            throw InvalidUserException("email is empty", Throwable("email"))
        if(user.password.isBlank())
            throw InvalidUserException("password is empty", Throwable("password"))
        return repository.insertUser(user)
    }
}