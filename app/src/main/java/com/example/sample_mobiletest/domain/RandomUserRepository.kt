package com.example.sample_mobiletest.domain

import com.example.sample_mobiletest.datasource.RandomUserRemoteDataSource

class RandomUserRepository(private val randomUserRemoteDataSource: RandomUserRemoteDataSource) {

    suspend fun getRandomUser() = randomUserRemoteDataSource.getRandomUser()

}