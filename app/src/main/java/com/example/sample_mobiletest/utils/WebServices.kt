package com.example.sample_mobiletest.utils

import com.example.sample_mobiletest.domain.model.ResponseApi
import retrofit2.http.GET

interface WebServices {//Interface para hacer llamado de la API.

    @GET("/api/")
    suspend fun getRandomUser(): ResponseApi
}