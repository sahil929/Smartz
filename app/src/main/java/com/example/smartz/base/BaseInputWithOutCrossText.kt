package com.example.smartz.base

import android.content.Context
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import android.text.Editable
import android.util.AttributeSet
import com.example.smartz.R
import com.google.android.material.textfield.TextInputLayout


public class BaseInputWithOutCrossText(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) :
    TextInputEditText(context, attrs) {
    constructor(context: Context) : this(context, null) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.editTextStyle
    ) {

    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

                if (arg3 > 0) {
                    if ((parent?.parent as? TextInputLayout)?.error != "") {
                        (parent?.parent as? TextInputLayout)?.error = null
                    }

                }

            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            }

            override fun afterTextChanged(arg0: Editable) {
            }
        })
    }

}