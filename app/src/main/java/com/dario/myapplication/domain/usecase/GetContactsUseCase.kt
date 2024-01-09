package com.dario.myapplication.domain.usecase

import com.dario.myapplication.core.UseCase
import com.dario.myapplication.data.repository.RandomUserRepositoryImpl
import com.dario.myapplication.domain.model.Contact
import javax.inject.Inject

class GetContactsUseCase @Inject constructor (
    private val randomUserRepository: RandomUserRepositoryImpl
): UseCase<GetContactsUseCase.Params, List<Contact>> {

    override suspend fun invoke(params: Params): Result<List<Contact>> {
        return randomUserRepository.getContacts(
            page = params.page,
            numberOfResults = params.numberOfResults
        )
    }

    data class Params(
        val page: Int,
        val numberOfResults: Int
    )

}