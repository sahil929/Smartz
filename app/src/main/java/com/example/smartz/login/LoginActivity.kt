package com.example.smartz.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smartz.model.LoginModel
import com.example.smartz.BR
import com.example.smartz.R
import com.example.smartz.base.BaseActivity
import com.example.smartz.databinding.ActivityLoginBinding
import com.example.smartz.model.BookSlotModel
import com.example.smartz.network.ResponseStatus
import com.example.smartz.register.RegisterVM
import com.example.smartz.utils.BaseDialog
import com.example.smartz.utils.BasePaymentDialog
import com.example.smartz.utils.Prefrences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    var viewDataBinding: ActivityLoginBinding? = null
    var model: LoginModel = LoginModel()
    var list: ArrayList<String>? = ArrayList<String>()
    var viewModel: LoginVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        viewDataBinding!!.loginModel= model
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.loginViewModel = viewModel
        btnLogin.setOnClickListener {
            if(model.mobileNo==null || model.password!!.isEmpty()){
                Toast.makeText(this, "Please fill the Login Details...", Toast.LENGTH_SHORT).show()
            }else {

                viewModel!!.loginUser(model).observe(this, Observer {
                    when(it.status){
                        ResponseStatus.SUCCESS->{
                            loginProgressBar.visibility= View.GONE
                            if(it.data!!.statusCode == 200){
                               Prefrences.getPrefernceInstance().saveLoginDetail(this,model.mobileNo)
                               loginProgressBar.visibility= View.GONE
                               startHomeActivity()
                           }else{
                               Toast.makeText(this,"Bad Credentials",Toast.LENGTH_SHORT).show()
                           }

                        }
                        ResponseStatus.ERROR->{
                            loginProgressBar.visibility= View.GONE
                        }
                        ResponseStatus.LOADING->{
                            loginProgressBar.visibility= View.VISIBLE
                        }
                    }
                })
            }
        }
     /*   tvRegister.setOnClickListener {
            startRegisterActivity()
        }
        tvForgotPassword.setOnClickListener {
            startForgotPasswordActivity()
        }*/
/*
        viewModel.loginObserver.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                viewModel.validateForm(it)?.observe(this, Observer {
                    Log.e("testingStatus", "" + it.status)

                    when (it.status) {
                        ResponseStatus.SUCCESS -> {
                            hideProgressBar(loginProgressBar)
                            Prefrences.getPrefernceInstance().saveLoginToken(
                                this,
                                it.data?.data?.userId,
                                it.data?.data?.accessToken,
                                it.data?.data?.isProfileComplete,
                                it.data?.data?.isPaymentComplete
                            )
                            if (it.data?.data?.isProfileComplete!!) {
                                startLeadsActivity()
                            } else {
                                startProfileActivity()
                                //neosha4@gmail.com
                                //Atm@12345
                            }
                        }
                        ResponseStatus.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                            hideProgressBar(loginProgressBar)
                        }
                        ResponseStatus.LOADING -> {
                            showProgressBar(getString(R.string.please_wait), loginProgressBar)
                        }
                    }
                })
            }
        })
*/

    }

}
