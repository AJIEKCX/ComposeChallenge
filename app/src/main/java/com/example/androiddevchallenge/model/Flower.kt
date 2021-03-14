package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class Flower(
    val title: String,
    @DrawableRes
    val res: Int
)

val flowers = listOf(
    "Monstera" to R.drawable.monstera,
    "Aglaonema" to R.drawable.aglaonema,
    "Peace lily" to R.drawable.peace_lily,
    "Fiddle leaf tree" to R.drawable.fiddle_leaf_tree,
    "Snake plant" to R.drawable.snake_plant,
    "Pothos" to R.drawable.pothos,
).map {
    Flower(
        title = it.first,
        res = it.second
    )
}