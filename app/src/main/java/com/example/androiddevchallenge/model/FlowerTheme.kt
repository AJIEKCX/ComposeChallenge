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
package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class FlowerTheme(
    val title: String,
    @DrawableRes
    val res: Int
)

val flowerThemes = listOf(
    "Desert chic" to R.drawable.desert_chic,
    "Tiny terrariums" to R.drawable.tiny_terrariums,
    "Jungle vibes" to R.drawable.jungle_vibes,
    "Easy care" to R.drawable.easy_care,
    "Statements" to R.drawable.statements,
).map {
    FlowerTheme(
        title = it.first,
        res = it.second
    )
}
