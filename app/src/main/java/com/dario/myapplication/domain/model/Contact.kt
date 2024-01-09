package com.dario.myapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Contact (
    val gender: Gender,
    val name: String,
    val surname: String,
    val coordinates: Coordinates,
    val email: String,
    val registerDate: LocalDateTime,
    val phone: String,
    val id: String,
    val picture: String,
): Parcelable

enum class Gender {
    FEMALE, MALE
}

@Parcelize
data class Coordinates (
    val latitude: Double,
    val longitude: Double
): Parcelable