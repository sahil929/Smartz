package com.example.smartz.bookings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartz.R
import com.example.smartz.databinding.ActivityMyBookingsBinding
import com.example.smartz.model.Booking
import com.example.smartz.network.ResponseStatus
import com.example.smartz.utils.Prefrences
import kotlinx.android.synthetic.main.activity_my_bookings.*
import kotlinx.android.synthetic.main.toolbar.*

class MyBookingsActivity : AppCompatActivity() {
    var viewDataBinding: ActivityMyBookingsBinding? = null
    var model:MyBookingVM = MyBookingVM()
    var bookingList :List<Booking> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_bookings)
        val viewModel: MyBookingVM = ViewModelProvider(this).get(MyBookingVM::class.java)
        viewDataBinding!!.lifecycleOwner = this
        viewModel.getMyBookings().observe(this, Observer {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    progressBarlist.visibility = View.GONE
                    if(it.data!=null){
                        if(!it.data.body.isNullOrEmpty()){
                            viewDataBinding!!.tvNoDataAvailable.visibility = View.GONE
                            bookingList = it.data.body.filter { it.mobileNumber.toString() == Prefrences.getPrefernceInstance().getUserId(this).toString() }
                            if(!bookingList.isNullOrEmpty()){
                                viewDataBinding!!.rlSlots.visibility = View.VISIBLE
                                setAdapter(bookingList)
                            }else{
                                viewDataBinding!!.rlSlots.visibility = View.GONE
                                viewDataBinding!!.tvNoDataAvailable.visibility = View.VISIBLE
                            }
                        }
                        else{
                            viewDataBinding!!.rlSlots.visibility = View.GONE
                            viewDataBinding!!.tvNoDataAvailable.visibility = View.VISIBLE
                        }
                    }

                }
                ResponseStatus.ERROR -> {
                    progressBarlist.visibility = View.GONE
                }
                ResponseStatus.LOADING -> {
                    progressBarlist.visibility = View.VISIBLE
                }
            }

        })
        ivBack.setOnClickListener { onBackPressed() }
        tvTitle.text = "My Bookings"
    }
    private fun setAdapter(data: List<Booking>) {
        viewDataBinding!!.rlSlots?.apply {
            val  layoutManager = LinearLayoutManager(this@MyBookingsActivity)
            val adapter = MyBookingAdapter(this@MyBookingsActivity,data)
            viewDataBinding!!.rlSlots.layoutManager = layoutManager
            viewDataBinding!!.rlSlots.adapter = adapter
        }
    }

}