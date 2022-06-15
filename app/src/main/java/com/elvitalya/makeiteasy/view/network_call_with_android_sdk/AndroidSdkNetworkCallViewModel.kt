package com.elvitalya.makeiteasy.view.network_call_with_android_sdk

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.elvitalya.makeiteasy.utils.showToast
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class AndroidSdkNetworkCallViewModel @Inject constructor(
    private val app: Application
) : ViewModel() {

    var response by mutableStateOf(UserProfileResponse())

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var httpURLConnection: HttpURLConnection

    private fun apiRequest() {

        try {
            val call = Runnable {
                val url = URL("https://reqres.in/api/users?page=2")
                httpURLConnection = url.openConnection() as HttpURLConnection
                val bufferReader = BufferedReader(
                    InputStreamReader(httpURLConnection.inputStream)
                )
                val jsonStringHolder = StringBuilder()

                while (true) {
                    val readLine = bufferReader.readLine() ?: break
                    jsonStringHolder.append(readLine)
                }

                val data =
                    Gson().fromJson(jsonStringHolder.toString(), UserProfileResponse::class.java)
                handler.post {
                    response = data
                }

            }
            executor.execute(call)
        } catch (e: Exception) {
            showToast(app, "Error...")
        }
    }

    init {
        apiRequest()
    }
}


data class UserProfileResponse(
    val page: String = "",
    val per_page: String = "",
    val total: String = "",
    val total_pages: String = "",
    val data: List<UserEntity> = listOf()
)

data class UserEntity(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)