package com.example.smartz.model

data class RegisterModel(

    /**
     * {
    "licenseNo": "234344",
    "mobileNo": 991540010702,
    "name": "sahil",
    "password": "12222",
    "statusValue": 0
    }
     */
   // var userId: String?="",
    var name: String?="",
   // var lastName: String?="",
    var mobileNo: Int?=0,
    var password: String?="",
    var licenseNo: String?="",
    var statusValue:Int?= 0


/* //--------------------------------------
 var accountNumber: String?="",
 var addressId: String?="",
 var addressLine1: String?="",
 var addressLine2: String?="",
 var addressType: String?="",
 var city: String?="",
 var dateOfBirth: String?="",
 var otp: String?="",
 var designation: String?="",
 var emailId: String?="",
 var userId: String?="",
 var firstName: String?="",
 var gender: String?="",
 var lastName: String?="",
 var mobileNumber: String?="",
 var panNumber: String?="",
 var password: String?="",
 var confirmPassword: String?="",
 var role: String?="franchise",
 var isMobileVerified: Boolean?=false,
 var isEmailVerified: Boolean?=true,
 var profilePicture: String?=""*/
)