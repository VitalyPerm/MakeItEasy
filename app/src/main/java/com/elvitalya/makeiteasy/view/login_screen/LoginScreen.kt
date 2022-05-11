package com.elvitalya.makeiteasy.view.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.ui.theme.Teal200
import com.elvitalya.makeiteasy.utils.showToast
import com.elvitalya.makeiteasy.view.main.Screens


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    var emailVal by remember { mutableStateOf("") }
    var passwordVal by remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.login_img),
            contentDescription = "Login image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Cyan.copy(alpha = 0.3f)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoginTitle()

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = emailVal,
                onValueChange = { emailVal = it },
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "Email address") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = passwordVal,
                onValueChange = { passwordVal = it },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.password_eye),
                            contentDescription = "password",
                            tint = if (passwordVisibility.value) Purple500 else Teal200
                        )
                    }
                },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = if (passwordVisibility.value)
                    VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    when {
                        emailVal.isEmpty() -> {
                            showToast(context, "Please enter email address")
                        }
                        passwordVal.isEmpty() -> {
                            showToast(context, "Please enter password")
                        }
                        else -> {
                            showToast(context, "Success!!!")
                        }
                    }
                },
                modifier = Modifier
                    .size(90.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(90.dp)
            ) {
                Text(text = "Sign In")
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Text(
                text = "Create an Account?",
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screens.Registration.route)
                    }
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun LoginTitle() {
    Text(
        text = "Sign In",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}







































