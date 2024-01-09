package com.dario.myapplication.data.datasource.repository

import com.dario.myapplication.domain.model.Contact

interface RandomUserRepository {
    suspend fun getContacts(page: Int, numberOfResults: Int): Result<List<Contact>>
}