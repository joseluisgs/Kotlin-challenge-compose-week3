/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.HomeScreen
import com.example.androiddevchallenge.ui.LoginScreen
import com.example.androiddevchallenge.ui.WelcomeScreen

sealed class Screen(val route: String, @StringRes val name: Int, val icon: Int?) {
    object WelcomeScreen : Screen("welcome_screen", R.string.home_section_welcome, null)
    object LoginScreen : Screen("login_screen", R.string.home_section_login, null)
    object HomeScreen : Screen("home_screen", R.string.home_section_home, R.drawable.ic_home)
    object ProfileScreen :
        Screen("profile_screen", R.string.home_section_profile, R.drawable.ic_profile)
}

@Composable
fun BuildNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}
