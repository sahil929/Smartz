package com.example.smartz.slots

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartz.R
import com.example.smartz.base.BaseActivity
import com.example.smartz.databinding.ActivitySlotDetailBinding
import com.example.smartz.model.Booking
import com.example.smartz.model.BookingModel
import com.example.smartz.network.ResponseStatus
import kotlinx.android.synthetic.main.activity_slot_detail.*
import kotlinx.android.synthetic.main.toolbar.*


class SlotDetailActivity : BaseActivity() {
    var viewDataBinding: ActivitySlotDetailBinding? = null
    var model: SlotDetailVm = SlotDetailVm()
    var list: ArrayList<String>? = ArrayList<String>()
    var bookingList :List<Booking> = ArrayList()
    var viewModel: SlotDetailVm?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_slot_detail)
        viewModel = ViewModelProvider(this).get(SlotDetailVm::class.java)

        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.slotDetailVm = viewModel
        viewModel!!.slotLoction.postValue(intent.getStringExtra("slotLocation").toString())
        viewModel!!.slotName.postValue(intent.getStringExtra("slotName").toString())
        viewModel!!.slotId = intent.getIntExtra("slotId",0)

       viewModel!!.bookSlotButtonClicked.observe(this, Observer {

           it.getContentIfNotHandled().let{
               startBookSlotActivity(viewModel!!.slotId,bookingList)
           }
       })
        ivBack.setOnClickListener { onBackPressed() }
        tvTitle.text = "Slot Detail"
    }
    private fun setAdapter(data: List<Booking>?) {
        viewDataBinding!!.rlSlots?.apply {
            val  layoutManager = LinearLayoutManager(this@SlotDetailActivity)
            val adapter = BookingSlotListAdapter(this@SlotDetailActivity,data)
            viewDataBinding!!.rlSlots.layoutManager = layoutManager
            viewDataBinding!!.rlSlots.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel!!.getBooking().observe(this, Observer {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    slotProgressBar.visibility = View.GONE
                    if(it.data!=null){
                        if(!it.data.body.isNullOrEmpty()){
                            viewDataBinding!!.tvNoDataAvailable.visibility = View.GONE
                            bookingList = it.data.body.filter { it.slotId==viewModel!!.slotId }
                            if(!bookingList.isNullOrEmpty()){
                                setAdapter(bookingList)
                                viewDataBinding!!.tvNoDataAvailable.visibility = View.GONE
                            }else{
                                viewDataBinding!!.tvNoDataAvailable.visibility = View.VISIBLE
                            }
                           }else{
                            viewDataBinding!!.tvNoDataAvailable.visibility = View.VISIBLE
                        }
                    }

                }
                ResponseStatus.ERROR -> {
                    slotProgressBar.visibility = View.GONE
                }
                ResponseStatus.LOADING -> {
                    slotProgressBar.visibility = View.VISIBLE
                }
            }

        })

    }
}