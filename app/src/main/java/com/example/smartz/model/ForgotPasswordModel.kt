package com.example.smartz.model

data class ForgotPasswordModel(
    var oldPassword: String? = "",
    var newPassword: String? = "",
    var emailId: String? = ""
)
