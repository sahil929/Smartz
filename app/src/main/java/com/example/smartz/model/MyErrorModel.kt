package com.example.smartz.model

data class MyErrorModel(
    val error: String,
    val message: String,
    val path: String,
    val timestamp: String
)