package com.example.smartz.carParked

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.smartz.R
import com.example.smartz.base.BaseActivity
import com.example.smartz.databinding.CarParkedLayoutBinding
import com.example.smartz.home.HomeVM
import kotlinx.android.synthetic.main.car_parked_layout.*


class CarParked : BaseActivity() {

    var viewDataBinding: CarParkedLayoutBinding? = null
   // var model: HomeVM = HomeVM()
    var list: ArrayList<String>? = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.car_parked_layout)
        val viewModel: HomeVM = ViewModelProvider(this).get(HomeVM::class.java)
        viewDataBinding!!.lifecycleOwner = this

        val animation = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, -1.3f)
        animation.duration = 4000
        animation.fillAfter = true
        animation.setAnimationListener(MyAnimationListener())

        viewDataBinding!!.carPark.startAnimation(animation)

    }

   inner class MyAnimationListener : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            tvParkCar.visibility=View.VISIBLE
        }
        override fun onAnimationRepeat(animation: Animation) {}
        override fun onAnimationStart(animation: Animation) {}
    }
}