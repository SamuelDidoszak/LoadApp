package com.didoszak.loadapp.feature_add_find_job.domain.util

sealed class Resource<T>(val data: T?, val message :String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
}