package com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.repository

import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.model.UserResponse
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.network.ApiInterface
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<List<UserResponse>> {
        val response = try {
            apiInterface.getUserData()
        } catch (e: Exception) {
            return Resource.Error("An error occurred : ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

}