package com.didoszak.loadapp.feature_add_find_job.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val id: Int? = null,
    @PrimaryKey val email: String,
    val password: String,
    val typeId: Int? = null,
    val organizationName: Int? = null,
    val organizationNIP: Int? = null,
    val info: String? = null,
    val image: String? = null,
)

class InvalidUserException(message: String, cause: Throwable): Exception(message, cause)