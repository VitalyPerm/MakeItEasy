package com.elvitalya.makeiteasy.view.nested_lazy_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elvitalya.makeiteasy.R

@Composable
fun NestedLazyList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        repeat(20) {
            item { NestedLazyItem(it) }
        }
    }
}

@Composable
fun NestedLazyItem(index: Int) {

    LazyRow {
        repeat(20) {
            item {
                Row(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_radio_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = "column $index number $it")
                }
            }
        }
    }
}