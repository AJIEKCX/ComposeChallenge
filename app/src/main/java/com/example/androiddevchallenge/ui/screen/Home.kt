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
package com.example.androiddevchallenge.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.transform.RoundedCornersTransformation
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Flower
import com.example.androiddevchallenge.model.FlowerTheme
import com.example.androiddevchallenge.model.flowerThemes
import com.example.androiddevchallenge.model.flowers
import com.example.androiddevchallenge.ui.widget.BloomTextField
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.systemBarsPadding

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(Tabs.HOME) }
    val tabs = Tabs.values()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(stringResource(tab.title)) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) }
                    )
                }
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .navigationBarsPadding(),
    ) {
        HomeContent(
            Modifier.systemBarsPadding(),
            flowerThemes,
            flowers,
            searchText,
            onSearchChanged = { searchText = it }
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    flowerThemes: List<FlowerTheme>,
    flowers: List<Flower>,
    searchText: String,
    onSearchChanged: (String) -> Unit,
) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        item {
            BloomTextField(
                label = { Text(stringResource(R.string.home_search_label)) },
                value = searchText,
                onValueChange = onSearchChanged,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )
        }
        item {
            Text(
                stringResource(R.string.home_browse_themes_title),
                Modifier.paddingFromBaseline(top = 32.dp),
                style = MaterialTheme.typography.h1
            )
        }
        item {
            LazyRow(contentPadding = PaddingValues(top = 16.dp)) {
                items(flowerThemes) { item ->
                    ThemeCard(item)
                }
            }
        }
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.home_design_your_home_garden_title),
                    Modifier.paddingFromBaseline(top = 32.dp),
                    style = MaterialTheme.typography.h1
                )
                Icon(
                    Icons.Default.FilterList,
                    contentDescription = null,
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
        }
        items(flowers) { item ->
            FlowerItem(item)
        }
    }
}

@Composable
private fun ThemeCard(flowerTheme: FlowerTheme) {
    Card(
        Modifier
            .size(136.dp)
            .padding(end = 8.dp, bottom = 8.dp),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column {
            Image(
                painter = painterResource(flowerTheme.res),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f),
                contentScale = ContentScale.Crop
            )
            Text(
                flowerTheme.title,
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun FlowerItem(flower: Flower) {
    var checked by remember { mutableStateOf(false) }
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        val (image, title, description, checkBox, divider) = createRefs()
        CoilImage(
            data = flower.res,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(64.dp),
            contentScale = ContentScale.Crop,
            requestBuilder = {
                transformations(
                    RoundedCornersTransformation(
                        topLeft = 16.dp.value,
                        topRight = 16.dp.value,
                        bottomLeft = 16.dp.value,
                        bottomRight = 16.dp.value
                    )
                )
            }
        )
        Text(
            flower.title,
            Modifier
                .constrainAs(title) {
                    start.linkTo(image.end, margin = 16.dp)
                    top.linkTo(parent.top)
                }
                .paddingFromBaseline(top = 24.dp),
            style = MaterialTheme.typography.h2
        )
        Text(
            stringResource(R.string.home_description),
            Modifier
                .constrainAs(description) {
                    start.linkTo(title.start)
                    top.linkTo(title.bottom)
                },
            style = MaterialTheme.typography.body1
        )
        Checkbox(
            modifier = Modifier
                .constrainAs(checkBox) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colors.onSecondary)
        )
        Divider(
            Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 80.dp)
        )
    }
}

private enum class Tabs(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.bottom_nav_home, Icons.Default.Home),
    FAVOURITES(R.string.bottom_nav_favourites, Icons.Default.FavoriteBorder),
    PROFILE(R.string.bottom_nav_profile, Icons.Default.AccountCircle),
    CART(R.string.bottom_nav_cart, Icons.Default.ShoppingCart),
}
