package com.example.smartz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartz.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tvCreateAccount.setOnClickListener {
            startRegisterActivity()
        }
        tvAlreadyAccount.setOnClickListener {
            startLoginActivity()
        }
    }
}