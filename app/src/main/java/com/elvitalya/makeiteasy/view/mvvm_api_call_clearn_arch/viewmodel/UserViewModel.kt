package com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvitalya.makeiteasy.utils.showToast
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.model.UserResponse
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.network.ApiInterface
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.repository.UserRepository
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext private val context: Context,
    private val apiInterface: ApiInterface
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    val data = MutableSharedFlow<List<UserResponse>?>()

    private fun getUserData() {
        viewModelScope.launch {
            isLoading.value = true

            when (val result = userRepository.getUserResponse()) {
                is Resource.Success -> {
                    data.emit(result.data)
                    isLoading.value = false
                    showToast(context, "Success...")
                }
                is Resource.Loading -> {
                    showToast(context, "Loading.... !!!")
                }
                is Resource.Error -> {
                    showToast(context, "Error...")
                }
            }
        }
    }

    private fun getUserDataWithCall() {
        apiInterface.getUserDataWithCall().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                viewModelScope.launch {
                    data.emit(response.body())
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                showToast(context, "Error...")
            }

        })
    }

    init {
        getUserData()
        //getUserDataWithCall()
    }
}