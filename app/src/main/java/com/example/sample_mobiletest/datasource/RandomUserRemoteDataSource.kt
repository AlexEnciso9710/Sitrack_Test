package com.example.sample_mobiletest.datasource

import com.example.sample_mobiletest.utils.Result.Error
import com.example.sample_mobiletest.utils.Result.Success
import com.example.sample_mobiletest.utils.WebServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserRemoteDataSource {//Clase para obtener los datos de la URL con retrofit.

    suspend fun getRandomUser() = try {
        val retrofit = Retrofit.Builder().baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        val webServices: WebServices = retrofit.create(WebServices::class.java)

        val result = webServices.getRandomUser()
        Success(result.userInfo[0])
    } catch (exception: Exception) {
        Error(exception)
    }

    private fun getOkHttpClient() = OkHttpClient.Builder().apply {//Constructor
        addInterceptor(getHttpInterceptor())

    }.build()

    private fun getHttpInterceptor() = HttpLoggingInterceptor().apply {//
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
