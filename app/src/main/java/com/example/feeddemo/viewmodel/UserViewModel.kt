package com.example.feeddemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feeddemo.model.entity.User
import com.example.feeddemo.model.repository.UserRepository
import com.example.feeddemo.pojo.RequestModel
import com.example.feeddemo.pojo.ViewState
import kotlinx.coroutines.launch
import java.io.IOException

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
     val data = userRepository.data
    val dataObserve : MutableLiveData<ViewState<LiveData<List<User>>>> = MutableLiveData()

    init {
        dataObserve.postValue(ViewState.Success(data))
    }

    fun fetchUserList(requestModel: RequestModel) {
        viewModelScope.launch {
            try {
                dataObserve.postValue(ViewState.Loading)
                userRepository.refreshUserList(requestModel)
                dataObserve.postValue(ViewState.Success(data))
            } catch (e: Exception) {
                if (e is IOException)
                    dataObserve.postValue(ViewState.NetworkError)
                else dataObserve.postValue(ViewState.Error(e.message.toString()))
                e.printStackTrace()
            }
        }
    }
}