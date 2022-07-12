package com.example.smartz.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartz.BR
import com.example.smartz.R
import com.example.smartz.databinding.CustomSlotInfoBinding
import com.example.smartz.model.Body
import com.example.smartz.model.SlotDetail

class SlotListAdapter(
    var context: Context,
    var data: SlotDetail?
) : RecyclerView.Adapter<SlotListAdapter.ViewHolder>() {

    private var list:List<Body>? = ArrayList<Body>()

     init {
         list = data?.body
     }

    // Inflating Layout and ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotListAdapter.ViewHolder {
        val binding: CustomSlotInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.custom_slot_info, parent, false)
        return SlotListAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = list!!.size

    // Bind data
    override fun onBindViewHolder(holder: SlotListAdapter.ViewHolder, position: Int) {
        holder.bind(list!!.get(position))
        holder.binding.tvViewDetail.setOnClickListener {
            (context as HomeActivity).startBookingDetailActivity(list!!.get(position))
        }
    }

    // Creating ViewHolder
    class ViewHolder(val binding: CustomSlotInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.user = data as Body
            binding.setVariable(BR.user, data)
            if(data.parkingId.equals("1")){
                binding.slotStatusIndicator.setBackgroundColor(Color.parseColor("#1996f3"));
            }else {
                binding.slotStatusIndicator.setBackgroundColor(Color.parseColor("#4CAF50"));

            }
            binding.executePendingBindings()
        }
    }


}