package com.example.smartz.register

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smartz.R
import com.example.smartz.base.BaseActivity
import com.example.smartz.databinding.ActivityRegisterBinding
import com.example.smartz.model.RegisterModel
import com.example.smartz.network.ResponseStatus
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    var viewDataBinding: ActivityRegisterBinding? = null
    var model: RegisterModel = RegisterModel()
    var countDownTimer: CountDownTimer? = null
    var viewModel: RegisterVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterVM::class.java)
        viewDataBinding!!.registerModel= model
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.registerViewModel = viewModel
        btnRegister.setOnClickListener {
            if(model.name!!.isEmpty()||model.licenseNo!!.isEmpty()||model.mobileNo==null||model.password!!.isEmpty()){
                Toast.makeText(this, "Please fill the Registration Details...", Toast.LENGTH_SHORT).show()
            } else {
                viewModel!!.registerUser(model).observe(this, Observer {
                   when(it.status){
                       ResponseStatus.SUCCESS->{
                           loginProgressBar.visibility= View.GONE
                           startLoginActivity()
                       }
                       ResponseStatus.ERROR->{

                       }
                       ResponseStatus.LOADING->{
                           loginProgressBar.visibility= View.VISIBLE
                       }
                   }
                })
            }


            //    startHomeActivity() }
        }
    }
}
