package com.example.smartz.bookings

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartz.R
import com.example.smartz.databinding.ItemSlotBinding
import com.example.smartz.model.Booking

class AddSlotAdapter(
    var context: Context,
    var alreadyBookedListed: List<Booking>?,
    var date: String
) : RecyclerView.Adapter<AddSlotAdapter.ViewHolder>() {

   // private var list: List<Booking>? = ArrayList<Booking>()

    private var defaultTimeList: List<String> = arrayListOf("1:00AM-3:00AM","3:00AM-5:00AM",
        "5:00AM-7:00AM","7:00AM-9:00AM","9:00AM-11:00AM","11:00AM-1:00PM",
        "1:00PM-3:00PM","3:00PM-5:00PM","5:00PM-7:00PM","7:00PM-9:00PM","9:00PM-11:00PM","11:00PM-1:00AM")

    init {
       // list = data
    }

    // Inflating Layout and ViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddSlotAdapter.ViewHolder {
        val binding: ItemSlotBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_slot, parent, false
        )
        return AddSlotAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = defaultTimeList!!.size

    // Bind data
    override fun onBindViewHolder(holder: AddSlotAdapter.ViewHolder, position: Int) {
        holder.bind(defaultTimeList!!.get(position))

        if(!alreadyBookedListed.isNullOrEmpty()){

            for (book in alreadyBookedListed!!){

                if(book.startDate.equals(date,ignoreCase = true)){
                    for (i in 0 until defaultTimeList!!.size) {
                        if(book.slot.equals(defaultTimeList!!.get(position))){
                            holder.binding.layoutItemSlot.isEnabled = false
                            holder.binding.itemTime.setTextColor(Color.GRAY)
                        } else{
                            holder.binding.layoutItemSlot.isEnabled = true
                            holder.binding.itemTime.setTextColor(Color.BLACK)
                        }
                    }
                }
            }
        }

        holder.binding.layoutItemSlot.setOnClickListener{
            if(holder.binding.layoutItemSlot.isSelected){
                holder.binding.layoutItemSlot.isSelected = false
                holder.binding.layoutItemSlot.setBackgroundDrawable(context.getDrawable(R.drawable.round_layout_bg))
                var selectedTime=""
                (context as BookSlotActivity).selectedTime(selectedTime)
            } else {
                holder.binding.layoutItemSlot.isSelected = true
                holder.binding.layoutItemSlot.setBackgroundDrawable(context.getDrawable(R.drawable.selected_drawable))
                var selectedTime=holder.binding.itemTime.text.toString()
                (context as BookSlotActivity).selectedTime(selectedTime)
            }
        }

    }

    // Creating ViewHolder
    class ViewHolder(val binding: ItemSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
         //   binding.booking = data as Booking
            binding.itemTime.setText(data)
            binding.executePendingBindings()
        }
    }



    }


