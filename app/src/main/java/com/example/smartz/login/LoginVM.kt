package com.example.smartz.login

import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartz.base.BaseVM
import com.example.smartz.base.Event
import com.example.smartz.model.LoginModel
import com.example.smartz.model.RegisterModel
import com.example.smartz.network.LoginResponseModel
import com.example.smartz.network.NetworkResponseType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.util.regex.Pattern

class LoginVM: BaseVM() {
    enum class ERRORS {
        ENTER_MOBILE, INVALID_MOBILE,
        ENTER_PASSWORD, INVALID_PASSWORD,
    }

    var loginRepo: LoginRepo
    val errors = ObservableArrayList<ERRORS>()
    var loginObserver = MutableLiveData<Event<LoginModel>>()

    init {

        loginRepo = LoginRepo()

    }

    /*
    this is to login user
     */
    fun loginUser(loginModel: LoginModel): LiveData<NetworkResponseType<LoginResponseModel>> {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<LoginModel> =
            moshi.adapter<LoginModel>(LoginModel::class.java)

        val json = jsonAdapter.toJson(loginModel)
        Log.e("LoginPostJson", "" + json)
        return loginRepo.loginUser(loginModel)
    }

    fun validateForm(loginModel: LoginModel): LiveData<NetworkResponseType<LoginModel>>? {
        if (isFormValid(loginModel)) {
            //  return loginUser(loginModel = loginModel)
        }
        return null
    }

    fun submitForm(login: LoginModel) {
        loginObserver.value = Event(login)
    }

    fun isFormValid(registerModel: LoginModel): Boolean {
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

        if (registerModel.password.isNullOrEmpty()) {
            errors.add(ERRORS.ENTER_PASSWORD)
        } else {
            val pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{8,40})"
            val p: Pattern = Pattern.compile(pattern)
            val found: Boolean = p.matcher(registerModel.password).lookingAt()
            if (!found) {
                errors.add(ERRORS.INVALID_PASSWORD)
            }
        }


        /* if (registerModel.confirmPassword.isNullOrEmpty()) {
             errors.add(ERRORS.ENTER_CONFIRM_PASSWORD)
         }*/


        /*if (registerModel.userId.isNullOrEmpty()) {
            errors.add(ERRORS.ENTER_MOBILE)

        } else if(!Patterns.EMAIL_ADDRESS.matcher(registerModel.userId.toString().trim()).matches())  {
            if(!Patterns.PHONE.matcher(registerModel.userId.toString()).matches()||registerModel.userId.toString().length!=10){
                errors.add(ERRORS.INVALID_MOBILE)
            }else{
               registerModel.emailId = registerModel.userId.toString()
            }
        }else{
            registerModel.emailId = registerModel.userId.toString()
        }
        return errors.isEmpty()
    }*/
        return false
    }
}


