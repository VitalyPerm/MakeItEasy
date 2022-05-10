package com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.model.UserResponse
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.viewmodel.UserViewModel

@Composable
fun CallApi(
    viewModel: UserViewModel = hiltViewModel()
) {

    val data = viewModel.data.collectAsState(initial = null)

    if (viewModel.isLoading.value) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan.copy(alpha = 0.3f))
        ) {
            data.value?.let {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    items(it) {
                        UserListItem(it)
                    }
                }
            }
        }
    }
}


@Composable
fun UserListItem(userResponse: UserResponse) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = userResponse.id.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Title ${userResponse.title}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "Status ${userResponse.completed}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )


        }
    }
}