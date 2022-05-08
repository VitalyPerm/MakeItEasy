package com.elvitalya.makeiteasy.view.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.Screens
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.utils.getJsonFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleGrid(navController: NavController) {
    val context = LocalContext.current

    val dataFileString = getJsonFromAsset(context, "GridData.json")

    val gson = Gson()

    val gridSampleType = object : TypeToken<List<GridData>>() {}.type

    var sampleData: List<GridData> = gson.fromJson(dataFileString, gridSampleType)

    Column(
        modifier = Modifier
            .background(Color.White)
            .wrapContentSize(Center)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Make it easy Grid",
                color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }

        LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.padding(10.dp)) {
            items(sampleData) { data ->
                SampleDataGridItem(data, navController)
            }
        }
    }
}

@Composable
fun SampleDataGridItem(data: GridData, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                val item = Gson().toJson(data)
                navController.navigate("${Screens.GridDetail.route}/${item}")
            }
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mie_img),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )

            Spacer(modifier = Modifier.padding(3.dp))

            Text(
                text = data.name,
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = data.desc,
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun GridDetails(data: GridData) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            contentAlignment = Center
        ) {
            Text(
                text = "Make it easy Grid Detail",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))

        Image(
            painter = painterResource(id = R.drawable.mie_img),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = data.name, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = data.desc,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}