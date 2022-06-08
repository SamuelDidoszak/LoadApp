package com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases

data class UserUseCases(
    val getUser: GetUser,
    val insertUser: InsertUser,
    val deleteUser: DeleteUser
)