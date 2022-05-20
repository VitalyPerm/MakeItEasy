package com.elvitalya.makeiteasy.view.bottom_app_bar_with_fab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvitalya.makeiteasy.ui.theme.Teal200


const val home = "Home"
const val settings = "Settings"

@Composable
fun BottomAppBarWithFab() {

    val content = remember { mutableStateOf("$home Screen") }
    val selectedItem = remember { mutableStateOf(home) }
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Bottom App Bar with FAB")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            content.value = "Navigation Drawer"
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                }, backgroundColor = Color.Cyan,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(15.dp)
            ) {
                Text(
                    text = content.value,
                    color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
                FloatAlertDialog(openDialog = openDialog)
            }
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                },
                shape = RoundedCornerShape(50),
                backgroundColor = Teal200
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
            BottomAppBar(
                cutoutShape = RoundedCornerShape(50),
                content = {
                    BottomNavigation {

                        BottomNavigationItem(
                            selected = selectedItem.value == home,
                            onClick = {
                                content.value = "$home Screen"
                                selectedItem.value = home
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = home) },
                            alwaysShowLabel = false
                        )

                        BottomNavigationItem(
                            selected = selectedItem.value == settings,
                            onClick = {
                                content.value = "$settings Screen"
                                selectedItem.value = settings
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Settings,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = settings) },
                            alwaysShowLabel = false
                        )
                    }
                }
            )
        }
    )
}

@Composable
fun FloatAlertDialog(
    openDialog: MutableState<Boolean>
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Floating Action", fontWeight = FontWeight.Bold) },
            text = { Text(text = "Some Dialog text") },
            confirmButton = {
                Button(onClick = { openDialog.value = false }) { Text(text = "Ok") }
            }
        )
    }
}