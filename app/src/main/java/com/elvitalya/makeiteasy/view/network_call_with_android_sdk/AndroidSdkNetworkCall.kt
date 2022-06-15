package com.elvitalya.makeiteasy.view.network_call_with_android_sdk

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AndroidSdkNetworkCall(
    viewModel: AndroidSdkNetworkCallViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ExampleText(text = viewModel.response.page)
            }
            item {
                ExampleText(text = viewModel.response.per_page)
            }
            item {
                ExampleText(text = viewModel.response.total)
            }
            item {
                ExampleText(text = viewModel.response.total_pages)
            }
            items(viewModel.response.data) {
                ExampleText(text = "${it.first_name} ${it.last_name} ${it.email}")
            }
        }
    }
}


@Composable
fun ExampleText(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.Yellow.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, Color.Cyan),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}