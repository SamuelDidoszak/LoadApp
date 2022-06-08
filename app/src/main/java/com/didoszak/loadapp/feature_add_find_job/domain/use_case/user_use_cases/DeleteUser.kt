package com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases

import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.repository.UserRepository

class DeleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        return repository.deleteUser(user)
    }
}