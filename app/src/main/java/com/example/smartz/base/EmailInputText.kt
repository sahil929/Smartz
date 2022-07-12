package com.example.smartz.base

import android.content.Context
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import android.text.Editable
import android.util.AttributeSet
import android.view.MotionEvent
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.smartz.R


public class EmailInputText(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) :
    TextInputEditText(context, attrs) {
    constructor(context: Context) : this(context, null) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.editTextStyle
    ) {

    }


    /*override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

                if (arg3 > 5) {
                     if(Patterns.EMAIL_ADDRESS.matcher(cs.toString()).matches()){
                         setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_green_tick, 0);
                     }
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }

            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            }

            override fun afterTextChanged(arg0: Editable) {
            }
        })
        this.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    event != null &&
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                    event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                ) {
                    if (event == null || !event.isShiftPressed()&&!text.isNullOrEmpty()) {
                        if(Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()){
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_green_tick, 0);
                        }
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        })
        this.setOnFocusChangeListener(object : OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
              if(!hasFocus&&!text.isNullOrEmpty()){
                  if(Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()){
                      setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_green_tick, 0);
                  }
              }
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCompoundDrawables()[2] != null) {
                if (event.getRawX() >= (getRight() - getCompoundDrawables()[2].getBounds()
                        .width())
                ) {
                    Log.e("tess", "called")
                    setText("")
                    return true;
                }
            }
        }
        return super.onTouchEvent(event)
    }*/
}