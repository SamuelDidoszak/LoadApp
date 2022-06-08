package com.didoszak.loadapp.feature_add_find_job.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.didoszak.loadapp.feature_add_find_job.domain.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "UserDB"
    }
}