package com.dario.myapplication.domain.usecase

import com.dario.myapplication.core.UseCase
import com.dario.myapplication.domain.model.Contact
import javax.inject.Inject

class FilterContactsUseCase @Inject constructor (): UseCase<FilterContactsUseCase.Params, List<Contact>> {

    override suspend fun invoke(params: Params): Result<List<Contact>> {
        return if (params.input.isBlank()) {
            Result.success(params.contacts)
        } else {
            val input = params.input.trimEnd()
            val filteredResults = params.contacts.filter { contact: Contact ->
                contact.name.uppercase().contains(input, true) ||
                        "${contact.name} ${contact.surname}".contains(input, true) ||
                        contact.surname.uppercase().contains(input, true) ||
                        contact.email.uppercase().contains(input, true)
            }
            Result.success(filteredResults)
        }
    }

    data class Params(
        val input: String,
        val contacts: List<Contact>
    )

}