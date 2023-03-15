package com.example.sample_mobiletest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_mobiletest.domain.GetRandomUserUseCase
import com.example.sample_mobiletest.domain.model.UserInfo
import com.example.sample_mobiletest.domain.model.toInfoUser
import com.example.sample_mobiletest.ui.model.InfoUser
import com.example.sample_mobiletest.ui.model.MapsUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel(private val getRandomUserUseCase: GetRandomUserUseCase) : ViewModel() {

    private val _mapsUiModel = MutableLiveData<MapsUiModel>()
    val mapsUiModel: LiveData<MapsUiModel>
        get() = _mapsUiModel

    fun getUser() {
        emitMapsUiState(showRefresh = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = getRandomUserUseCase.getRandomUser()
            withContext(Dispatchers.Main) {
                when(result) {
                    is com.example.sample_mobiletest.utils.Result.Success -> getUserSuccess(result.data)
                    is com.example.sample_mobiletest.utils.Result.Error -> getUserError(result.exception)
                }
            }
        }
    }

    private fun getUserSuccess(userInfo: UserInfo) {
        emitMapsUiState(infoUser = userInfo.toInfoUser())
    }

    private fun getUserError(exception: Exception) {
        exception.printStackTrace()
        emitMapsUiState(exception = exception)
    }

    private fun emitMapsUiState(showRefresh: Boolean = false, infoUser: InfoUser? = null, exception: Exception? = null) {
        _mapsUiModel.value = MapsUiModel(showRefresh = showRefresh, infoUser = infoUser, exception = exception)
    }
}