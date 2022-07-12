package com.example.smartz.model

data class CovidModel(
    var emailId: String? = "",
    var userId: String? = "",
    var firstName: String? = "",
    var lastName: String? = "",
    var mobileNumber: String? = "",
    var isMobileVerified: Boolean? = false,
    var isEmailVerified: Boolean? = true,
    var otp: String? = ""
)