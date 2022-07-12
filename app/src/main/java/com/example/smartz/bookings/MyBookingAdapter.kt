package com.example.smartz.bookings

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartz.R
import com.example.smartz.carParked.CarParked
import com.example.smartz.databinding.CustomMyBookiingBinding
import com.example.smartz.model.Booking
import com.example.smartz.register.RegisterActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyBookingAdapter(
    var context: Context,
    var data: List<Booking>
) : RecyclerView.Adapter<MyBookingAdapter.ViewHolder>() {

    private var list:List<Booking>? = ArrayList<Booking>()

     init {
         list = data
     }

    // Inflating Layout and ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookingAdapter.ViewHolder {
        val binding: CustomMyBookiingBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.custom_my_bookiing, parent, false)
        return MyBookingAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = list!!.size

    // Bind data
    override fun onBindViewHolder(holder: MyBookingAdapter.ViewHolder, position: Int) {
        val currentDate: String =
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val currentTime =
            SimpleDateFormat("HH", Locale.getDefault()).format(Date()).toInt()
        Log.e("sdsdsdsd",""+currentDate+" "+currentTime+" ")
        var timeIntervalInString = list?.get(position)?.slot!!.split("-")[0].split(":")
         var timeInterval = 0
         if(timeIntervalInString[1].contains("PM")){
             timeInterval = timeIntervalInString[0].toInt()+12
         }else{
             timeInterval = timeIntervalInString[0].toInt()
         }

        if( list?.get(position)?.slotStatus!=0){
            holder.binding.tvParkCar.text = "Park a vehicle"
        }else{
            holder.binding.tvParkCar.text = "Vehicle Parked"
        }
        if(currentTime>=timeInterval&&currentTime<timeInterval+2&&currentDate.equals( list?.get(position)?.startDate,ignoreCase = true)){
            holder.binding.tvParkCar.visibility = View.VISIBLE
        }else{
            holder.binding.tvParkCar.visibility = View.GONE
        }
        holder.binding.tvParkCar.setOnClickListener {
            context.startActivity(Intent(context, CarParked::class.java))
            (context as MyBookingsActivity).overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        }

        holder.bind(list!!.get(position))
    }

    // Creating ViewHolder
    class ViewHolder(val binding: CustomMyBookiingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.booking = data as Booking
            binding.executePendingBindings()
        }
    }


}