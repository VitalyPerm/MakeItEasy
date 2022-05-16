package com.elvitalya.makeiteasy.view.botton_nav

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elvitalya.makeiteasy.ui.theme.Purple500

enum class BottomNavigate(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    Home("Home", "Home", Icons.Filled.Home),
    Favorite("Favorite", "Favorite", Icons.Filled.Favorite),
    Search("Search", "Search", Icons.Filled.Search),
    Setting("Setting", "Setting", Icons.Filled.Settings),
    User("User", "User", Icons.Filled.Person)
}


val bottomNavigationItems = listOf(
    BottomNavigate.Home,
    BottomNavigate.Favorite,
    BottomNavigate.Search,
    BottomNavigate.Setting,
    BottomNavigate.User
)


@Composable
fun BottomNavigationScreen() {
    val navController: NavHostController = rememberNavController()
    val title = remember { mutableStateOf(BottomNavigate.Home.title) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple500,
                elevation = AppBarDefaults.TopAppBarElevation,
                title = {
                    Text(
                        text = title.value,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController, bottomNavigationItems, title)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigate.Home.route
        ) {
            composable(BottomNavigate.Home.route) { HomeNavHost() }

            composable(BottomNavigate.Favorite.route) { FavoriteNavHost() }

            composable(BottomNavigate.Search.route) { SearchNavHost() }

            composable(BottomNavigate.Setting.route) { SettingNavHost() }

            composable(BottomNavigate.User.route) { UserNavHost() }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    bottomNavigationItems: List<BottomNavigate>,
    title: MutableState<String>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavigate.Home.route
    val routes = remember { BottomNavigate.values().map { it.route } }

    if (currentRoute in routes) {
        BottomNavigation {
            bottomNavigationItems.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(text = screen.route) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (screen.route != currentRoute) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            title.value = screen.title
                        }
                    }
                )
            }
        }
    }
}