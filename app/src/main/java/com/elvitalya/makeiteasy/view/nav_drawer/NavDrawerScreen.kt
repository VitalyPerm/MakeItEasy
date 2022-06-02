package com.elvitalya.makeiteasy.view.nav_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


private val drawerItems = listOf(
    DrawerMenuItem(
        id = "Home",
        title = "Home",
        contentDescription = "go home",
        icon = Icons.Default.Home
    ),
    DrawerMenuItem(
        id = "settings",
        title = "Settings",
        contentDescription = "go settings",
        icon = Icons.Default.Settings
    ),
    DrawerMenuItem(
        id = "Help",
        title = "Help",
        contentDescription = "get Help",
        icon = Icons.Default.Info
    )
)


@Composable
fun NavDrawerScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DrawerAppBar {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            NavDrawerHeader()
            DrawerBody(
                items = drawerItems,
                modifier = Modifier,
                onItemClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    when (it.id) {
                        "Home" -> {
                            navController.navigate(NavDrawerScreens.Home.route)
                        }
                        "settings" -> {
                            navController.navigate(NavDrawerScreens.Settings.route)
                        }
                        "Help" -> {
                            navController.navigate(NavDrawerScreens.Help.route)
                        }
                    }
                })
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) {
        NavHost(
            navController = navController,
            startDestination = NavDrawerScreens.Home.route
        ) {
            composable(NavDrawerScreens.Home.route) {
                DrawerHome()
            }

            composable(NavDrawerScreens.Settings.route) {
                DrawerSettings()
            }
            composable(NavDrawerScreens.Help.route) {
                DrawerHelp()
            }
        }
    }
}


@Composable
fun NavDrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Header",
            fontSize = 60.sp
        )
    }
}

@Composable
fun DrawerAppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Navigation Drawer")
        },
        backgroundColor = Color.Yellow,
        contentColor = Color.Magenta,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle Drawer"
                )
            }
        }
    )
}


@Composable
fun DrawerBody(
    items: List<DrawerMenuItem>,
    modifier: Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (DrawerMenuItem) -> Unit
) {
    LazyColumn(
        modifier
    ) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    modifier = Modifier
                        .weight(1f),
                    style = itemTextStyle
                )
            }
        }
    }
}

@Composable
fun DrawerHome() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "HOME")
    }
}

@Composable
fun DrawerHelp() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Help")
    }
}

@Composable
fun DrawerSettings() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings")
    }
}

sealed class NavDrawerScreens(val route: String) {
    object Home : NavDrawerScreens("Home")
    object Settings : NavDrawerScreens("Settings")
    object Help : NavDrawerScreens("Help")
}