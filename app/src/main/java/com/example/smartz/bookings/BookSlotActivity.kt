package com.example.smartz.bookings

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartz.R
import com.example.smartz.databinding.ActivityBookSlotBinding
import com.example.smartz.model.BookSlotModel
import com.example.smartz.model.Booking
import com.example.smartz.network.ResponseStatus
import com.example.smartz.utils.Prefrences
import kotlinx.android.synthetic.main.activity_book_slot.*
import kotlinx.android.synthetic.main.activity_book_slot.slotProgressBar
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_slot_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.collections.ArrayList

class BookSlotActivity : AppCompatActivity() {

    var viewDataBinding: ActivityBookSlotBinding? = null
    var bookSlotVm: BookSlotVm = BookSlotVm()
    var list: ArrayList<String>? = ArrayList<String>()
    var model = BookSlotModel()
    var selectedTimeSlot: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_slot)
        val viewModel: BookSlotVm = ViewModelProvider(this).get(BookSlotVm::class.java)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.bookSlotVm = viewModel
        viewDataBinding!!.bookingSlot.visibility = View.GONE

        var slotId = intent.extras?.get("slotId")
        var bookingList: List<Booking> = intent.getParcelableArrayListExtra("bookings")!!

        submit_booking.setOnClickListener {
            if (editText1.text!!.isEmpty() || edit_date.text!!.isEmpty() || selectedTimeSlot!!.isEmpty()) {
                Toast.makeText(this, "Please fill the details first...", Toast.LENGTH_SHORT).show()
            } else {
                model.slotId = slotId as Int
                model.userMobile= Prefrences.getPrefernceInstance().getUserId(this).toString()
                model.vehicleNumber = editText1.text.toString()
                viewModel.bookaSlot(model).observe(this, Observer {
                    when(it.status){
                        ResponseStatus.SUCCESS->{
                            slotProgressBar.visibility= View.GONE
                            Toast.makeText(
                                this,
                                "Booking has been done successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            edit_date.setText("")
                            editText1.setText("")
                            onBackPressed()
                            Log.e("sucess a book slot", "Scuss book a slot")
                        }
                        ResponseStatus.ERROR->{
                            slotProgressBar.visibility= View.GONE
                        }
                        ResponseStatus.LOADING->{
                            slotProgressBar.visibility= View.VISIBLE
                        }
                    }
                })
            }
        }
        edit_date.setOnFocusChangeListener { view, b ->
            if(view.hasFocus()){
               viewModel.clickToSelectDate()
            }
        }
        viewModel.clickToSelectDate.observe(this, Observer {

            it.getContentIfNotHandled().let {

                var cldr = Calendar.getInstance()
                var day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                var month: Int = cldr.get(Calendar.MONTH)
                var year: Int = cldr.get(Calendar.YEAR)


                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        var moo :String =""
                        if(dayOfMonth.toString().length==1){
                            moo = "0"+dayOfMonth
                        }else{
                            moo = dayOfMonth.toString()
                        }
                        var date = "" + moo + "-" + (monthOfYear+1)+ "-" + year
                        model.startDate = date
                        edit_date.setText(date)
                        viewDataBinding!!.bookingSlot.visibility = View.VISIBLE
                        setAdapter(bookingList,date)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        })
        ivBack.setOnClickListener { onBackPressed() }
        tvTitle.text = "Book Slot"
    }


    private fun setAdapter(
        data: List<Booking>?,
        date: String
    ) {
        viewDataBinding!!.bookingSlot?.apply {
            val layoutManager = LinearLayoutManager(this@BookSlotActivity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            val adapter = AddSlotAdapter(this@BookSlotActivity, data,date)
            viewDataBinding!!.bookingSlot.layoutManager = layoutManager
            viewDataBinding!!.bookingSlot.adapter = adapter
        }
    }

    fun selectedTime(slotTime: String) {
        selectedTimeSlot = slotTime
        model.Slot = selectedTimeSlot
    }
}