package com.example.smartz.register

import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.base.BaseVM
import com.example.smartz.base.Event
import com.example.smartz.model.*
import com.example.smartz.network.NetworkResponse
import com.example.smartz.network.NetworkResponseType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.util.regex.Pattern

class RegisterVM : BaseVM() {

    enum class ERRORS {
        ENTER_NAME, ENTER_LAST_NAME, ENTER_MOBILE, INVALID_MOBILE, ENTER_OTP, INVALID_OTP, ENTER_EMAIL, INVALID_EMAIL,
        ENTER_PASSWORD, INVALID_PASSWORD, ENTER_OLD_PASSWORD, OLD_INVALID_PASSWORD, ENTER_CONFIRM_PASSWORD, INVALID_CONFIRM_PASSWORD, ENTER_DESIGNATION,
    }

    var regisRepository: RegisterRepo
    val errors = ObservableArrayList<ERRORS>()
    var registerObserver = MutableLiveData<Event<String>>()
    var otpObserver = MutableLiveData<Event<RegisterModel>>()
    var verifyOtpObserver = MutableLiveData<Event<RegisterModel>>()
    var resendOtpObserver = MutableLiveData<Event<RegisterModel>>()

    init {
        regisRepository = RegisterRepo()
    }

    /*
    this is to register user
     */
    fun registerUser(user: RegisterModel): LiveData<NetworkResponseType<NetworkResponse>> {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<RegisterModel> =
            moshi.adapter<RegisterModel>(RegisterModel::class.java)

        val json = jsonAdapter.toJson(user)
        Log.e("RegisteredPostJson", "" + json)

        return regisRepository.registerUser(user)
    }



    /*  fun submitFormAfterValidate(registerModel: RegisterModel): LiveData<NetworkResponseType<LoginModel>>? {
        return registerUser(user = registerModel)
    }*/

   /* fun submitForm(registerModel: RegisterModel) {
        registerObserver.value = Event(registerModel)
    }*/

    fun otpButtonClicked(registerModel: RegisterModel) {
        otpObserver.value = Event(registerModel)
    }

    fun verifyOtpButtonClicked(registerModel: RegisterModel) {
        verifyOtpObserver.value = Event(registerModel)
    }

    fun resendOtpButtonClicked(registerModel: RegisterModel) {
        resendOtpObserver.value = Event(registerModel)
    }

    fun isFormValid(registerModel: RegisterModel): Boolean {
        errors.clear()

//        if (userAddress.value!!.cityArea == null) {
//            userAddress.value!!.cityArea = BaseLocation()
//        }
//
//        if (userAddress.value!!.district == null) {
//            userAddress.value!!.district = BaseLocation()
//        }
//        if (userAddress.value!!.cityArea.displayName.isNullOrEmpty()) {
//            errors.add(errors.MISSING_CITY)
//        }
//
//        if (userAddress.value!!.district.displayName.isNullOrEmpty()) {
//            errors.add(errors.MISSING_DISTRICT)
//        }
//        if(!KSAUtils.isValidAddress(userAddress.value!!.address1.toString().trim()))
//        {
//            errors.add(errors.INVALID_STREET_BUILDING)
//        }
/*
        if (registerModel.firstName.isNullOrEmpty() || registerModel.firstName.toString()
                .trim().length <= 0
        ) {
            errors.add(ERRORS.ENTER_NAME)
        }

        if (registerModel.password.isNullOrEmpty() || registerModel.password.toString()
                .trim().length <= 0
        ) {
            errors.add(ERRORS.ENTER_PASSWORD)
        } else {
            val pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{8,40})"
            val p: Pattern = Pattern.compile(pattern)
            val found: Boolean = p.matcher(registerModel.password).lookingAt()
            if (!found) {
                errors.add(ERRORS.INVALID_PASSWORD)
            }
        }


        */
/* if (registerModel.confirmPassword.isNullOrEmpty()) {
             errors.add(ERRORS.ENTER_CONFIRM_PASSWORD)
         }*//*


        if (registerModel.lastName.isNullOrEmpty() || registerModel.lastName.toString()
                .trim().length <= 0
        ) {
            errors.add(ERRORS.ENTER_LAST_NAME)
        }

        if (registerModel.mobileNumber.isNullOrEmpty() || registerModel.mobileNumber.toString()
                .trim().length <= 0
        ) {
            errors.add(ERRORS.ENTER_MOBILE)

        } else if (!Patterns.PHONE.matcher(registerModel.mobileNumber.toString().trim())
                .matches() || registerModel.mobileNumber?.trim()?.length != 10
        ) {
            errors.add(ERRORS.INVALID_MOBILE)
        }

        return errors.isEmpty()
    }

    fun validateMobile(registerModel: RegisterModel): Boolean {
        errors.clear()
        if (registerModel.mobileNumber.isNullOrEmpty() || registerModel.mobileNumber.toString()
                .trim().length <= 0
        ) {
            errors.add(ERRORS.ENTER_MOBILE)

        } else if (!Patterns.PHONE.matcher(registerModel.mobileNumber.toString().trim())
                .matches() || registerModel.mobileNumber?.trim()?.length != 10
        ) {
            errors.add(ERRORS.INVALID_MOBILE)
        }
        return errors.isEmpty()
    }
*/
        return false
    }

}