package com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases

import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.domain.repository.Api
import com.didoszak.loadapp.feature_add_find_job.domain.repository.ApiRepository
import com.didoszak.loadapp.feature_add_find_job.domain.util.Resource
import retrofit2.Response

class GetAllQualifications (
    private val repository: ApiRepository
) {
    suspend operator fun invoke(): Resource<List<Qualification>> {
        return repository.getQualifications()
    }
}