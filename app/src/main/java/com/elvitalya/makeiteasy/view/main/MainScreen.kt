package com.elvitalya.makeiteasy.view.main

import android.os.Build
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.view.alert_dialog.CallDialog
import com.elvitalya.makeiteasy.view.botton_nav.BottomNavigationScreen
import com.elvitalya.makeiteasy.view.circular_progress_bar.CircularProgressBarScreen
import com.elvitalya.makeiteasy.view.exoplayer.ExoPlayer
import com.elvitalya.makeiteasy.view.expandable_list.ExpandableListScreen
import com.elvitalya.makeiteasy.view.grid.GridData
import com.elvitalya.makeiteasy.view.grid.GridDetails
import com.elvitalya.makeiteasy.view.grid.SampleGrid
import com.elvitalya.makeiteasy.view.grid_shimmer.ShimmerGridAnimate
import com.elvitalya.makeiteasy.view.list_with_search.ListWithSearch
import com.elvitalya.makeiteasy.view.login_screen.LoginScreen
import com.elvitalya.makeiteasy.view.login_screen.RegisterScreen
import com.elvitalya.makeiteasy.view.material_component.MaterialComponent
import com.elvitalya.makeiteasy.view.mvvm_api_call_clearn_arch.view.CallApi
import com.elvitalya.makeiteasy.view.pick_image.ImagePicker
import com.elvitalya.makeiteasy.view.sample_list.SampleData
import com.elvitalya.makeiteasy.view.sample_list.SampleDataDetails
import com.elvitalya.makeiteasy.view.sample_list.SampleList
import com.elvitalya.makeiteasy.view.shimmer_animation.ShimmerAnimate
import com.elvitalya.makeiteasy.view.spinner_date_time_picker.SpinnerScreen
import com.google.gson.Gson
import kotlinx.coroutines.delay

private const val TAG = "MAIN"

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val startDestination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        Screens.MainScreen.route else Screens.SplashScreen.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.SampleData.route) {
            SampleList(navController = navController)
        }

        composable(Screens.Dialog.route) {
            CallDialog()
        }

        composable("${Screens.SampleDetail.route}/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, SampleData::class.java)
                SampleDataDetails(data = item)
            }
        }

        composable(Screens.GridData.route) {
            SampleGrid(navController = navController)
        }

        composable("${Screens.GridDetail.route}/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, GridData::class.java)
                GridDetails(data = item)
            }
        }

        composable(Screens.Login.route, content = {
            LoginScreen(navController = navController)
        })
        composable(Screens.Registration.route, content = {
            RegisterScreen(navController = navController)
        })

        composable(Screens.MainScreen.route, content = {
            MainScreen(navController = navController)
        })

        composable(Screens.BottomNavigation.route, content = {
            BottomNavigationScreen()
        })

        composable(Screens.CircularProgressBar.route, content = {
            CircularProgressBarScreen()
        })

        composable(Screens.MVVMCleanApiCall.route, content = {
            CallApi()
        })
        composable(Screens.SplashScreen.route, content = {
            SplashScreenAnimate(navController)
        })
        composable(Screens.Shimmer.route, content = {
            ShimmerAnimate()
        })
        composable(Screens.MaterialComponents.route, content = {
            MaterialComponent()
        })
        composable(Screens.ExoPlayer.route, content = {
            ExoPlayer()
        })
        composable(Screens.GridShimmer.route, content = {
            ShimmerGridAnimate()
        })
        composable(Screens.ImagePicker.route, content = {
            ImagePicker()
        })
        composable(Screens.ExpandableList.route, content = {
            ExpandableListScreen()
        })
        composable(Screens.SpinnerDateTimePicker.route, content = {
            SpinnerScreen()
        })
        composable(Screens.ListWithSearch.route, content = {
            ListWithSearch()
        })
    }
}


@Composable
fun MainScreen(navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            MainItem(name = Screens.Login.route, navController)
        }

        item {
            MainItem(name = Screens.SampleData.route, navController)
        }

        item {
            MainItem(name = Screens.GridData.route, navController = navController)
        }
        item {
            MainItem(name = Screens.BottomNavigation.route, navController = navController)
        }
        item {
            MainItem(name = Screens.Dialog.route, navController = navController)
        }
        item {
            MainItem(name = Screens.CircularProgressBar.route, navController = navController)
        }
        item {
            MainItem(name = Screens.MVVMCleanApiCall.route, navController = navController)
        }
        item {
            MainItem(name = Screens.Shimmer.route, navController = navController)
        }
        item {
            MainItem(name = Screens.MaterialComponents.route, navController = navController)
        }
        item {
            MainItem(name = Screens.ExoPlayer.route, navController = navController)
        }
        item {
            MainItem(name = Screens.GridShimmer.route, navController = navController)
        }
        item {
            MainItem(name = Screens.ImagePicker.route, navController = navController)
        }
        item {
            MainItem(name = Screens.ExpandableList.route, navController = navController)
        }
        item {
            MainItem(name = Screens.SpinnerDateTimePicker.route, navController = navController)
        }
        item {
            MainItem(name = Screens.ListWithSearch.route, navController = navController)
        }
    }
}

@Composable
fun MainItem(name: String, navController: NavController) {
    val width = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = Modifier
            .size(width / 3)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Cyan.copy(alpha = 0.5f))
            .clickable { navController.navigate(name) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center,

            )
    }
}

@Composable
fun SplashScreenAnimate(navController: NavController) {

    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 700,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                }
            )
        )
        delay(100L)
        navController.navigate(Screens.MainScreen.route) {
            popUpTo(Screens.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .scale(scale.value)
        )
    }
}


sealed class Screens(val route: String) {
    object MainScreen : Screens("Main")
    object SampleData : Screens("Sample List Data")
    object SampleDetail : Screens("Sample Detail")
    object GridData : Screens("Grid Data")
    object GridDetail : Screens("Grid Detail")
    object Login : Screens("Login")
    object Registration : Screens("Registration")
    object BottomNavigation : Screens("Bottom Navigation")
    object Dialog : Screens("Dialog")
    object CircularProgressBar : Screens("Circular Progress Bar")
    object MVVMCleanApiCall : Screens("MVVM Clean Api Call")
    object SplashScreen : Screens("SplashScreen")
    object Shimmer : Screens("Shimmer")
    object MaterialComponents : Screens("Material Components")
    object ExoPlayer : Screens("ExoPlayer")
    object GridShimmer : Screens("Grid Shimmer")
    object ImagePicker : Screens("Image Picker")
    object ExpandableList : Screens("Expandable List")
    object SpinnerDateTimePicker : Screens("Spinner Date Time Picker")
    object ListWithSearch : Screens("List with search")
}