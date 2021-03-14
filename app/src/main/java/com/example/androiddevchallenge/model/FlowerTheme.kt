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