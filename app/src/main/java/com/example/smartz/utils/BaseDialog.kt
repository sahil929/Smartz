package com.example.smartz.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment


class BaseDialog(var fragment: Fragment) :
    DialogFragment(), View.OnClickListener {

    interface DailogListener {
     //  fun onDialogPositiveClick(dialog: DialogFragment?, documentVerModel: DocumentVerModel)
        fun onDialogNegativeClick(dialog: DialogFragment?)
    }

    var listener: DailogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = fragment as DailogListener
    }

    override fun onClick(p0: View?) {
    }

}