package com.example.smartz.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Prefrences {

    var database: String = "smartz"
    var userId: String = "userID"
    var token: String = "token"
    var profileCompleted: String = "profileCompleted"
    var paymentCompleted: String = "paymentCompleted"

    companion object {
        var instance: Prefrences? = null
        fun getPrefernceInstance(): Prefrences {
            if (instance == null) {
                instance = Prefrences()
                return instance!!
            } else {
                return instance!!
            }
        }
    }

    fun saveLoginDetail(
        context: Context,
        userId: Int?
    ) {

        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = prefrences.edit()
        editor.putInt(this.userId, userId!!)
       editor.apply()
    }

    fun getUserToken(context: Context): String? {
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        return prefrences.getString(this.token, "")
    }

    fun getProfileCompleted(context: Context): Boolean {
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        return prefrences.getBoolean(this.profileCompleted, false)
    }
    fun getPaymentCompleted(context: Context): Boolean {
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        return prefrences.getBoolean(this.paymentCompleted, false)
    }

    fun getUserId(context: Context): Int? {
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        return prefrences.getInt(this.userId, 0)
    }

    fun clearDatabase(context: Context) {
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = prefrences.edit()
        editor.clear()
        editor.apply()
    }
    fun setProfileCompleted(context:Context,profileCompleted: Boolean?){
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = prefrences.edit()
        editor.putBoolean(this.profileCompleted, profileCompleted!!)
        editor.apply()
    }
    fun setPaymentCompleted(context:Context,paymentCompeted: Boolean?){
        val prefrences: SharedPreferences = context.getSharedPreferences(
            database,
            MODE_PRIVATE
        );
        val editor: SharedPreferences.Editor = prefrences.edit()
        editor.putBoolean(this.paymentCompleted, paymentCompeted!!)
        editor.apply()
    }
}