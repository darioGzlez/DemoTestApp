package com.dario.myapplication.testmockdata

import com.dario.myapplication.data.datasource.entity.Coordinates
import com.dario.myapplication.data.datasource.entity.Dob
import com.dario.myapplication.data.datasource.entity.Id
import com.dario.myapplication.data.datasource.entity.Location
import com.dario.myapplication.data.datasource.entity.Login
import com.dario.myapplication.data.datasource.entity.Name
import com.dario.myapplication.data.datasource.entity.Picture
import com.dario.myapplication.data.datasource.entity.RandomUserApiResponse
import com.dario.myapplication.data.datasource.entity.Registered
import com.dario.myapplication.data.datasource.entity.Street
import com.dario.myapplication.data.datasource.entity.Timezone

val fakeRandomUserApiResponse: RandomUserApiResponse = RandomUserApiResponse(
    gender = "male",
    name = Name("Mr", "John", "Doe"),
    location = createSampleLocation(),
    email = "john.doe@example.com",
    login = createSampleLogin(),
    dob = Dob("1990-01-01", 32),
    registered = Registered("2007-07-09T05:51:59.390Z", 2),
    phone = "123-456-7890",
    cell = "987-654-3210",
    id = Id("12345", "ID123"),
    picture = createSamplePicture(),
    nat = "US"
)

fun createSampleLocation(): Location {
    return Location(
        street = Street(123, "Main Street"),
        city = "Cityville",
        state = "Stateville",
        country = "Countryland",
        postcode = "12345",
        coordinates = Coordinates("12.345", "-67.890"),
        timezone = Timezone("-05:00", "EST")
    )
}

fun createSampleLogin(): Login {
    return Login(
        uuid = "123-456-789",
        username = "john_doe",
        password = "securePassword",
        salt = "randomSalt",
        md5 = "md5Hash",
        sha1 = "sha1Hash",
        sha256 = "sha256Hash"
    )
}

fun createSamplePicture(): Picture {
    return Picture(
        large = "https://example.com/large.jpg",
        medium = "https://example.com/medium.jpg",
        thumbnail = "https://example.com/thumbnail.jpg"
    )
}
