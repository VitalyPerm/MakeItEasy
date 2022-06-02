package com.elvitalya.makeiteasy.view.sample_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.utils.getJsonFromAsset
import com.elvitalya.makeiteasy.view.main.Screens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun SampleList(navController: NavController) {

    val context = LocalContext.current
    val dataFileString = getJsonFromAsset(context, "SampleData.json")
    val gson = Gson()
    val listSampleType = object : TypeToken<List<SampleData>>() {}.type
    val sampleData: List<SampleData> = gson.fromJson(dataFileString, listSampleType)

    Column(
        modifier = Modifier
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Make It Easy",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        AutoCompleteName(sampleData)

        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
        ) {
            items(sampleData) { data ->
                SampleDataListItem(data, navController)
            }
        }
    }
}

@Composable
fun AutoCompleteName(sampleData: List<SampleData>) {
    TODO("Not yet implemented")
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDoneActionCLick: () -> Unit = {},
    onClearCLick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { query ->
            onValueChanged(query)
        },
        modifier = modifier
            .fillMaxWidth(0.9f)
            .onFocusChanged { onFocusChanged(it) },
        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    onClearCLick()
                }
            ) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }
        },
        keyboardActions = KeyboardActions(onDone = { onDoneActionCLick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )
}


@Composable
fun SampleDataListItem(data: SampleData, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                val item = Gson().toJson(data)
                navController.navigate("${Screens.SampleDetail.route}/${item}")
            }
            .padding(10.dp)
            .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.mie_img),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = data.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = data.desc,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

class AutoCompleteState<T : AutoCompleteEntity>(private val startItems: List<T>) :
    AutoCompleteScope<T> {
    private var onItemSelectedBlock: ItemSelected<T>? = null

    fun selectItem(item: T) {
        onItemSelectedBlock?.invoke(item)
    }

    var filteredItems by mutableStateOf(startItems)

    override var isSearching by mutableStateOf(false)
    override var boxWidthPercentage by mutableStateOf(0.9f)
    override var shouldWrapContentHeight by mutableStateOf(false)
    override var boxMaxHeight by mutableStateOf(TextFieldDefaults.MinHeight * 3)
    override var boxBorderStoke by mutableStateOf(BorderStroke(2.dp, Color.Black))
    override var boxShape: Shape by mutableStateOf(RoundedCornerShape(8.dp))

    override fun filter(query: String) {
        filteredItems = startItems.filter { entity ->
            entity.filter(query)
        }
    }

    override fun onItemSelected(block: ItemSelected<T>) {
        onItemSelectedBlock = block
    }

}


private typealias ItemSelected<T> = (T) -> Unit

@Stable
interface AutoCompleteScope<T : AutoCompleteEntity> : AutoCompleteDesignScope {
    var isSearching: Boolean
    fun filter(query: String)
    fun onItemSelected(block: ItemSelected<T> = {})
}

@Stable
interface AutoCompleteDesignScope {
    var boxWidthPercentage: Float
    var shouldWrapContentHeight: Boolean
    var boxMaxHeight: Dp
    var boxBorderStoke: BorderStroke
    var boxShape: Shape
}

typealias CustomFilter<T> = (T, String) -> Boolean

fun <T> List<T>.isAutoCompleteEntities(filter: CustomFilter<T>): List<NameAutoCompleteEntity<T>> {
    return map {
        object : NameAutoCompleteEntity<T> {
            override val value: T
                get() = it

            override fun filter(query: String): Boolean {
                return filter(value, query)
            }
        }
    }
}

@Stable
interface NameAutoCompleteEntity<T> : AutoCompleteEntity {
    val value: T
}


@Stable
interface AutoCompleteEntity {
    fun filter(query: String): Boolean
}