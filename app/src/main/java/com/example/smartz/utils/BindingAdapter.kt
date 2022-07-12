package com.example.smartz.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("dateText")
fun dateText(textView:TextView,text:List<String>){
    textView.text=text.joinToString (separator = "\n")
}