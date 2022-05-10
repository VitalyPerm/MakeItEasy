package com.elvitalya.makeiteasy.view.alert_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500

@Composable
fun CallDialog() {
    var isDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShowDialog(isDialogOpen) {
            isDialogOpen = false
        }

        Button(
            onClick = {
                isDialogOpen = true
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.5f)
                .height(50.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Purple500)
        ) {
            Text(text = "Show Popup", color = Color.White)
        }
    }
}

@Composable
fun ShowDialog(isDialogOpen: Boolean, onDismiss: () -> Unit) {

    var emailVal by remember { mutableStateOf("") }
    var passVal by remember { mutableStateOf("") }

    var passVisibility by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester }

    if (isDialogOpen) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = "Login Popup",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_person_pin),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = emailVal,
                    onValueChange = { emailVal = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    value = passVal,
                    onValueChange = { passVal = it },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passVisibility = !passVisibility
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.password_eye),
                                contentDescription = null,
                                tint = if (passVisibility) Purple500 else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Password") },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    visualTransformation = if (passVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(35.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(Purple500)
                ) {
                    Text(
                        text = "Close",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}