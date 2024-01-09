package com.dario.myapplication.domain.usecase

import com.dario.myapplication.core.UseCase
import com.dario.myapplication.data.datasource.entity.RandomUserApiResponse
import com.dario.myapplication.domain.model.Contact
import com.dario.myapplication.domain.model.Coordinates
import com.dario.myapplication.domain.model.Gender
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

class MapContactUseCase @Inject constructor (): UseCase<RandomUserApiResponse, Contact> {

    override suspend fun invoke(params: RandomUserApiResponse): Result<Contact> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        try {
            val formattedDate = LocalDateTime.parse(params.registered?.date, formatter)
            val contact = Contact(
                id = params.id?.value ?: UUID.randomUUID().toString(),
                gender = if (params.gender == "male") {
                    Gender.MALE
                } else {
                    Gender.FEMALE
                },
                email = params.email ?: "",
                coordinates = Coordinates(
                    latitude = params.location?.coordinates?.latitude?.toDouble() ?: 0.0,
                    longitude =  params.location?.coordinates?.longitude?.toDouble() ?: 0.0
                ),
                registerDate = formattedDate,
                name = params.name?.first ?: "",
                surname = params.name?.last ?: "",
                phone = params.phone ?: "",
                picture = params.picture?.large ?: ""
            )
            return Result.success(contact)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}