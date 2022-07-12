package com.example.smartz.slots

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartz.R
import com.example.smartz.databinding.CustomSlotBookiingBinding
import com.example.smartz.databinding.CustomSlotInfoBinding
import com.example.smartz.model.Booking
import com.example.smartz.model.BookingModel

class BookingSlotListAdapter(
    var context: Context,
    var data: List<Booking>?
) : RecyclerView.Adapter<BookingSlotListAdapter.ViewHolder>() {

    private var list:List<Booking>? = ArrayList<Booking>()

     init {
         list = data
     }

    // Inflating Layout and ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingSlotListAdapter.ViewHolder {
        val binding: CustomSlotBookiingBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.custom_slot_bookiing, parent, false)
        return BookingSlotListAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = list!!.size

    // Bind data
    override fun onBindViewHolder(holder: BookingSlotListAdapter.ViewHolder, position: Int) {
        holder.bind(list!!.get(position))

    }

    // Creating ViewHolder
    class ViewHolder(val binding: CustomSlotBookiingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.booking = data as Booking
            binding.executePendingBindings()
        }
    }


}