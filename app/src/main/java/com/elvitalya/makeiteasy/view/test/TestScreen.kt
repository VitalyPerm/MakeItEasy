package com.elvitalya.makeiteasy.view.test

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Test(
    vm: TestViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(vm.inWorkState.size) { index ->
            Item(data = vm.inWorkState[index])
        }
        items(vm.finishedState.size) { index ->
            Item(data = vm.finishedState[index])
        }
        items(vm.newState.size) { index ->
            Item(data = vm.newState[index])
        }
    }
}


@Composable
fun Item(
    data: FakeData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = Color.Cyan,
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        Text(
            text = data.title, modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = data.type, modifier = Modifier
                .padding(16.dp)
        )
    }
}