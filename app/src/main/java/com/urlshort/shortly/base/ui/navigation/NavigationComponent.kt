package com.urlshort.shortly.base.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.urlshort.shortly.feature.home.ui.HomeScreen
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.home.destination
    ) {
        composable(NavigationDirections.home.destination) {
            HomeScreen()
        }
    }
}