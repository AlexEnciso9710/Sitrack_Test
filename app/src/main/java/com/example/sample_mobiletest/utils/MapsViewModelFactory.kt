package com.example.sample_mobiletest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sample_mobiletest.datasource.RandomUserRemoteDataSource
import com.example.sample_mobiletest.domain.GetRandomUserUseCase
import com.example.sample_mobiletest.domain.RandomUserRepository
import com.example.sample_mobiletest.ui.MapsViewModel

@Suppress("UNCHECKED_CAST")
class MapsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(
                    getRandomUserUseCase = GetRandomUserUseCase(
                            randomUserRepository = RandomUserRepository(
                                    randomUserRemoteDataSource = RandomUserRemoteDataSource()
                            )
                    )
            ) as T
        }
        throw java.lang.IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}