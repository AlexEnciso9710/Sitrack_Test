package com.example.sample_mobiletest.utils

import com.example.sample_mobiletest.domain.model.ResponseApi
import retrofit2.http.GET

interface WebServices {

    @GET("/api/")
    suspend fun getRandomUser(): ResponseApi
}