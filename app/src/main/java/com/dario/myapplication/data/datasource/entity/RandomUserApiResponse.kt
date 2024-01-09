package com.dario.myapplication.data.datasource.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val street: Street?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: Coordinates?,
    val timezone: Timezone?
)

@JsonClass(generateAdapter = true)
data class Street(
    val number: Int?,
    val name: String?
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    val latitude: String?,
    val longitude: String?
)

@JsonClass(generateAdapter = true)
data class Timezone(
    val offset: String?,
    val description: String?
)

@JsonClass(generateAdapter = true)
data class Login(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val salt: String?,
    val md5: String?,
    val sha1: String?,
    val sha256: String?
)

@JsonClass(generateAdapter = true)
data class Dob(
    val date: String?,
    val age: Int?
)

@JsonClass(generateAdapter = true)
data class Registered(
    val date: String?,
    val age: Int?
)

@JsonClass(generateAdapter = true)
data class Id(
    val name: String?,
    val value: String?
)

@JsonClass(generateAdapter = true)
data class Picture(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
)

@JsonClass(generateAdapter = true)
data class RandomUserApiResponse(
    val gender: String?,
    val name: Name?,
    val location: Location?,
    val email: String?,
    val login: Login?,
    val dob: Dob?,
    val registered: Registered?,
    val phone: String?,
    val cell: String?,
    val id: Id?,
    val picture: Picture?,
    val nat: String?
)

@JsonClass(generateAdapter = true)
data class Name(
    val title: String?,
    val first: String?,
    val last: String?
)

@JsonClass(generateAdapter = true)
data class Info(
    val seed: String?,
    val results: Int?,
    val page: Int?,
    val version: String?
)

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val results: List<RandomUserApiResponse> = emptyList(),
    val info: Info? = null
)