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

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.getData
import com.example.androiddevchallenge.navigation.Screen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.taupe100
import com.example.androiddevchallenge.ui.theme.taupe800
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomBarView(
                navController = navController,
                listOf(
                    Screen.HomeScreen,
                    Screen.ProfileScreen
                )
            )
        },
        floatingActionButton = {
            FloatingActionButtonView()
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        drawerElevation = 4.dp,
    ) { innerPadding ->
        ProvideWindowInsets {
            Box(
                Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .verticalScroll(scrollState)
                ) {
                    SearchView()
                    FavoriteList()
                    AlignBodyList()
                    AlignMindList()
                }
            }
        }
    }
}

@Composable
fun SearchView() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldSoothe(
            hint = stringResource(id = R.string.home_search_hint),
            icon = {
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colors.onSurface,
                )
            }
        )
    }
}

@Composable
fun FavoriteList() {
    TextSectionSoothe(
        label = stringResource(id = R.string.home_favorite_title)
    )
    val collection = getData()
        .take(6)
        .withIndex()
        .groupBy { it.index / 2 }

    LazyRow {
        collection
            .map { pair ->
                item {
                    if (collection.entries.first() == pair) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Column {
                        FavoriteItem(
                            name = pair.value[0].value.name,
                            url = pair.value[0].value.url
                        )
                        FavoriteItem(
                            name = pair.value[1].value.name,
                            url = pair.value[1].value.url
                        )
                    }
                    if (collection.entries.last() == pair) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
    }
}

@Composable
fun FavoriteItem(name: String, url: String) {
    Column {
        Row(
            modifier = Modifier
                .width(192.dp)
                .height(56.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clip(shape = MaterialTheme.shapes.small)
        ) {
            Column(
                modifier = Modifier
                    .weight(56f / 192)
                    .aspectRatio(1f)
                    .fillMaxHeight(),
            ) {
                CoilImage(
                    data = url,
                    contentDescription = name,
                    modifier = Modifier
                        .size(56.dp),
                    contentScale = ContentScale.Crop
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface),
                    ) {}
                }
            }
            Column(
                modifier = Modifier
                    .weight(150f / 192)
                    .background(MaterialTheme.colors.surface)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun AlignMindList() {
    TextSectionSoothe(
        label = stringResource(id = R.string.home_align_mind)
    )
    val items = getData().subList(fromIndex = 12, toIndex = 18)
    LazyRow {
        items.map {
            item {
                if (items.first() == it) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                CircleImage(name = it.name, url = it.url)
                if (items.last() == it) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

@Composable
fun AlignBodyList() {
    TextSectionSoothe(
        label = stringResource(id = R.string.home_align_body)
    )
    val items = getData().subList(fromIndex = 6, toIndex = 12)
    Column {
        LazyRow {
            items.map {
                item {
                    if (items.first() == it) {
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    CircleImage(name = it.name, url = it.url)
                    if (items.last() == it) {
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CircleImage(
    name: String,
    url: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(
            data = url,
            contentDescription = name,
            modifier = Modifier
                .size(88.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        ) {
            Column(
                modifier = Modifier
                    .size(88.dp)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colors.surface),
            ) {}
        }
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            text = name
        )
    }
}

@Composable
fun BottomBarView(navController: NavController, items: List<Screen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        val selectedColor = if (isSystemInDarkTheme()) taupe100 else taupe800
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon ?: R.drawable.ic_home),
                        contentDescription = screen.route,
                    )
                },
                selected = currentRoute == screen.route,
                selectedContentColor = selectedColor,
                unselectedContentColor = selectedColor.copy(alpha = .5f),
                label = {
                    Text(text = stringResource(id = screen.name))
                },
                onClick = {
                    navController.popBackStack(navController.graph.startDestination, false)
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun FloatingActionButtonView() {
    Column(
        modifier = Modifier
            .size(42.dp)
            .shadow(elevation = 4.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.ic_play),
            contentDescription = "Play",
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    MyTheme(darkTheme = false) {
        HomeScreen(rememberNavController())
    }
}
