package com.example.smartz.register.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.smartz.R
import com.example.smartz.model.StateModel


class AgeAdapter(val myContext: Context, val resource: Int, val objects: List<StateModel>) :
    ArrayAdapter<StateModel>(myContext, resource, objects) {
    override fun getViewTypeCount(): Int {
        return objects.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view :View  = LayoutInflater.from(myContext).inflate(R.layout.dropdown_menu_popup_item,parent,false)
        val tvName = view!!.findViewById(R.id.tvTitle) as TextView
        tvName.text = objects.get(position).state_name
        return view
    }

}