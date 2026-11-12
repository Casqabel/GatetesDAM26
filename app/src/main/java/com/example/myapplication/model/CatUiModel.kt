package com.example.myapplication.model

import java.util.UUID

data class CatUiModel(
    val gender: Gender,
    val name: String,
    val biography: String,
    val imageUrl: String,
    val id: String= UUID.randomUUID().toString()

)
