package com.dario.myapplication.data.datasource.api

import com.dario.myapplication.data.datasource.entity.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getContacts(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Result<ApiResponse>

}