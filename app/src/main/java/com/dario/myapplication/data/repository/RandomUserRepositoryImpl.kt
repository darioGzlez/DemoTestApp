package com.dario.myapplication.data.repository

import com.dario.myapplication.data.datasource.api.RandomUserApi
import com.dario.myapplication.data.datasource.repository.RandomUserRepository
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.usecase.MapContactUseCase
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val apiService: RandomUserApi,
    private val mapContactUseCase: MapContactUseCase
): RandomUserRepository {

    override suspend fun getContacts(page: Int, numberOfResults: Int): Result<List<Contact>> {
        return apiService.getContacts(
            page = page,
            results = numberOfResults
        ).mapCatching { response ->
            return Result.success(response.results.map { mapContactUseCase.invoke(it).getOrThrow() })
        }.getOrElse {
            Result.failure(it)
        }
    }

}