package com.elvitalya.makeiteasy.view.material_component

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.utils.showToast
import kotlinx.coroutines.delay

@Preview
@Composable
fun MaterialComponent() {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // Textview
        SimpleText()

        //Text field
        SimpleTextField()

        // Text field with Outline box
        TextFieldWithOutlineBox()

        //Text field Multiple line with Outline box
        TextFieldMultipleLineWithOutlineBox()

        //Normal button
        NormalButton(context)

        //Outline button
        OutlineButton(context)

        //Outline button with Icons
        OutlineButtonWithIcons(context)

        // Check box
        SimpleCheckBox()

        //RadioButton
        SimpleRadioButtons()

        //Toggle button
        SimpleToggleButton()

        //Switch
        SimpleSwitch()

        // LinearProgressIndicator
        SimpleLinearProgressIndicator()

        // CircularProgressIndicator()
        SimpleCircularProgressIndicator()

        //Slider - SeekBar
        SimpleSlider()
    }
}

@Composable
private fun SimpleSlider() {

    var sliderPosition by remember { mutableStateOf(0.5f) }

    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        modifier = Modifier
            .fillMaxWidth(0.8f)
    )
}

@Composable
private fun SimpleCircularProgressIndicator() {
    var progress by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = true) {
        while (true) {
            if (progress > 0.99f) progress = 0f else progress += 0.01f
            delay(30)
        }
    }
    CircularProgressIndicator(progress = progress)
}

@Composable
private fun SimpleLinearProgressIndicator() {
    var progress by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = true) {
        while (true) {
            if (progress > 0.99f) progress = 0f
            else progress += 0.01f
            delay(100)
        }
    }
    LinearProgressIndicator(progress = progress)
}

@Composable
private fun SimpleSwitch() {
    var switch by remember { mutableStateOf(false) }
    Switch(
        checked = switch,
        onCheckedChange = { switch = !switch },
        colors = SwitchDefaults.colors(Purple500)
    )
}

@Composable
private fun SimpleToggleButton() {
    var toggleCheck by remember { mutableStateOf(false) }
    IconToggleButton(
        checked = toggleCheck,
        onCheckedChange = { toggleCheck = !toggleCheck }
    ) {
        val tint = animateColorAsState(
            targetValue = if (toggleCheck) {
                Color(0xFFEC407A)
            } else {
                Color(0xFFB0BEC5)
            }
        )
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = tint.value
        )
    }
}

@Composable
private fun SimpleRadioButtons() {

    var checkedButton by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RadioButton(
            selected = checkedButton == 1,
            onClick = {
                checkedButton = 1
            },
            colors = RadioButtonDefaults.colors(Purple500)
        )
        Text(
            text = "First button",
            color = Purple500,
            fontSize = 16.sp
        )

        RadioButton(
            selected = checkedButton == 2,
            onClick = {
                checkedButton = 2
            },
            colors = RadioButtonDefaults.colors(Purple500)
        )
        Text(
            text = "Second button",
            color = Purple500,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SimpleCheckBox() {

    var checkedState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(30.dp, 0.dp, 30.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = !checkedState },
            colors = CheckboxDefaults.colors(Purple500)
        )
        Text(
            text = "Check Box",
            color = Purple500,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun OutlineButtonWithIcons(context: Context) {
    OutlinedButton(
        onClick = {
            showToast(context, "Clicked Outline button")
        },
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.5f)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
        )
        Text(
            text = "Submit",
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

@Composable
private fun OutlineButton(context: Context) {
    OutlinedButton(
        onClick = {
            showToast(context, "Clicked Outline button")
        },
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(8.dp),
        modifier = Modifier
            .fillMaxWidth(0.5f)
    ) {
        Text(
            text = "Submit",
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

@Composable
private fun NormalButton(context: Context) {
    Button(
        onClick = {
            showToast(context, "Clicked normal button")
        },
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.5f)
    ) {
        Text(
            text = "Submit",
            modifier = Modifier
                .padding(6.dp)
        )
    }
}

@Composable
private fun TextFieldMultipleLineWithOutlineBox() {

    var textStateThree by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textStateThree,
        onValueChange = { textStateThree = it },
        label = { Text(text = "Comment") },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        maxLines = 5,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Composable
private fun TextFieldWithOutlineBox() {

    var textStateTwo by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = textStateTwo,
        onValueChange = { textStateTwo = it },
        label = { Text(text = "Enter password") },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.password_eye),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    tint = if (passwordVisibility) Purple500 else Color.Gray
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
private fun SimpleTextField() {

    var textStateOne by remember { mutableStateOf("") }

    TextField(
        value = textStateOne,
        onValueChange = {
            textStateOne = it
        },
        label = { Text(text = "Enter name") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Composable
fun SimpleText() {
    Text(
        text = "JetPack Compose Material",
        color = Purple500,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}
