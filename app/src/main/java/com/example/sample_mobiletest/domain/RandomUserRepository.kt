package com.example.sample_mobiletest.domain

import com.example.sample_mobiletest.datasource.RandomUserRemoteDataSource

class RandomUserRepository(private val randomUserRemoteDataSource: RandomUserRemoteDataSource) {//Repositorio para enviar información al viewmodel.

    suspend fun getRandomUser() = randomUserRemoteDataSource.getRandomUser()

}