package com.example.smartz.base

import android.content.Context
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import android.text.Editable
import android.util.AttributeSet
import com.example.smartz.R


public class BasePasswordInputText(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) :
    TextInputEditText(context, attrs) {
    constructor(context: Context) : this(context, null) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.editTextStyle
    ) {

    }


  /*  override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_lock_24, 0);

        this.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

                Log.e(
                    "bhbhbh",
                    "" + arg1 + " " + arg2 + " " + arg3 + " " + getCompoundDrawables()[2]
                )
                if (arg1 > 0) {
                    if ((parent?.parent as? TextInputLayout)?.error != "") {
                        (parent?.parent as? TextInputLayout)?.error = null
                        if (getCompoundDrawables()[2] == null) {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_baseline_lock_24,
                                0
                            );
                            transformationMethod = PasswordTransformationMethod()
                            setSelection(cs.toString().length)

                        }
                    }

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
                    if (event == null || !event.isShiftPressed()) {
                        //setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        })
*//*
        this.setOnFocusChangeListener(object : OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
              if(!hasFocus&&!text.isNullOrEmpty()){
                  setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
              }
            }
        })
*//*
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCompoundDrawables()[2] != null) {
                if (getCompoundDrawables()[2] != null&&(ContextCompat.getDrawable(context,R.drawable.ic_baseline_lock_open_24)?.
                    constantState?.equals(getCompoundDrawables()[2].constantState)!!||ContextCompat.getDrawable(context,R.drawable.ic_baseline_lock_24)?.
                    constantState?.equals(getCompoundDrawables()[2].constantState)!!)) {
                    if (event.getRawX() >= (getRight() - getCompoundDrawables()[2].getBounds()
                            .width())
                    ) {
                        Log.e("tess", "called")
                        if (transformationMethod != null) {
                            transformationMethod = null
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_baseline_lock_open_24,
                                0
                            );

                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_baseline_lock_24,
                                0
                            );
                            transformationMethod = PasswordTransformationMethod()
                        }
                        return true;
                    }
                }

            }
        }
        return super.onTouchEvent(event)
    }*/
}