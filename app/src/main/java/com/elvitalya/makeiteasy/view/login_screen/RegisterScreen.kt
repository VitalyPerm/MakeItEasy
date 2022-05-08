package com.elvitalya.makeiteasy.view.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.Screens
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.utils.showToast

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()

    val nameVal = remember { mutableStateOf("") }
    val emailVal = remember { mutableStateOf("") }
    val phoneVal = remember { mutableStateOf("") }
    val passwordVal = remember { mutableStateOf("") }
    val confirmPasswordVal = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }


    Column(Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.register_img),
            contentDescription = "register",
            modifier = Modifier
                .size(175.dp)
                .align(CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Green.copy(alpha = 0.4f)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sign Up",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                OutlinedTextField(
                    value = nameVal.value,
                    onValueChange = { nameVal.value = it },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
            }

            item {
                OutlinedTextField(
                    value = emailVal.value,
                    onValueChange = { emailVal.value = it },
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "Email address") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
            }

            item {
                OutlinedTextField(
                    value = phoneVal.value,
                    onValueChange = { phoneVal.value = it },
                    label = { Text(text = "Phone number") },
                    placeholder = { Text(text = "Phone number") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }

            item {
                OutlinedTextField(
                    value = passwordVal.value,
                    onValueChange = { passwordVal.value = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = "password",
                                tint = if (passwordVisibility.value) Purple500 else Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
            }

            item {
                OutlinedTextField(
                    value = confirmPasswordVal.value,
                    onValueChange = { confirmPasswordVal.value = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value =
                                !confirmPasswordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = "password",
                                tint = if (confirmPasswordVisibility.value) Purple500 else Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }

            item {

                Button(onClick = {
                    when {
                        nameVal.value.isEmpty() -> {
                            showToast(context, "Please enter name!")
                        }
                        emailVal.value.isEmpty() -> {
                            showToast(context, "Please enter email!")
                        }
                        phoneVal.value.isEmpty() -> {
                            showToast(context, "Please enter phone number")
                        }
                        passwordVal.value.isEmpty() -> {
                            showToast(context, "Please enter password")
                        }
                        confirmPasswordVal.value.isEmpty() -> {
                            showToast(context, "Please confirm Password")
                        }
                        else -> {
                            showToast(context, "Success!")
                        }
                    }
                }
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
            }

            item {
                Text(text = "Login instead",
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.Login.route)
                    })

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    RegisterScreen(navController = rememberNavController())
}