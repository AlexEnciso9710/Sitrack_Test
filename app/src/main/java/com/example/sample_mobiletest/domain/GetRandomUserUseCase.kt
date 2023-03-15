package com.example.sample_mobiletest.domain

import com.example.sample_mobiletest.domain.model.UserInfo

class GetRandomUserUseCase (private  val randomUserRepository: RandomUserRepository) {

    suspend fun getRandomUser(): com.example.sample_mobiletest.utils.Result<UserInfo> {
        return randomUserRepository.getRandomUser()
    }
}