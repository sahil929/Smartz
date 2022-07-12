package com.example.smartz.model

data class UploadResponse(
    val document_path: String,
    val errorCode: Int,
    val message: String,
    val operation: String,
    val status: Boolean
)