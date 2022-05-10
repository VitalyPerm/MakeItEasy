package com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.network

import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.model.UserResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("todos")
    suspend fun getUserData(): List<UserResponse>
}