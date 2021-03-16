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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.navigation.Screen
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = if (isSystemInDarkTheme()) {
                painterResource(id = R.drawable.ic_dark_welcome)
            } else {
                painterResource(id = R.drawable.ic_light_welcome)
            },
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = if (isSystemInDarkTheme()) {
                painterResource(id = R.drawable.ic_dark_logo)
            } else {
                painterResource(id = R.drawable.ic_light_logo)
            },
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(32.dp))
        ButtonSoothe(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            text = stringResource(id = R.string.welcome_signup),
            backgroundColor = MaterialTheme.colors.primary,
            onClicked = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ButtonSoothe(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            text = stringResource(id = R.string.welcome_login),
            backgroundColor = MaterialTheme.colors.secondary,
            onClicked = { navController.navigate(Screen.LoginScreen.route) }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeScreenPreview() {
    MyTheme(darkTheme = false) {
        WelcomeScreen(rememberNavController())
    }
}
