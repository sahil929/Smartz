package com.example.smartz.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartz.*
import com.example.smartz.bookings.BookSlotActivity
import com.example.smartz.bookings.MyBookingsActivity
import com.example.smartz.home.HomeActivity
import com.example.smartz.login.LoginActivity
import com.example.smartz.model.Body
import com.example.smartz.model.Booking
import com.example.smartz.register.RegisterActivity
import com.example.smartz.slots.SlotDetailActivity
import com.example.smartz.utils.CustomProgressBar
import java.util.ArrayList


open class BaseActivity : AppCompatActivity() {
    private val progressBar = CustomProgressBar()
    var name: String = "name"
    var mobile: String = "mobile"
    var emailId: String = "emailId"
    var userId: String = "userId"
    var launchScreen: String = "launchScreen"
    var launchFromHomeScreen: String = "homeScreen"
    var launchFromRegisterScreen: String = "registerScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun startRegisterActivity() {
       /* startActivity(Intent(this, CarParked::class.java))
        finish()*/
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }
    fun startMyBookingActivity() {
        startActivity(Intent(this, MyBookingsActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startSlotDetailActivity(item: Body) {
        startActivity(
            Intent(this, SlotDetailActivity::class.java).putExtra("slotName", item.parkingName)
                .putExtra("slotLocation", item.parkingLocation)
                .putExtra("slotId", item.parkingId)
        )
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startBookSlotActivity(
        slotId: Int,
        bookingList: List<Booking>
    ) {
        startActivity(
            Intent(this, BookSlotActivity::class.java).putExtra("slotId", slotId)
                .putParcelableArrayListExtra("bookings", bookingList as ArrayList<Booking>)
        )
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        /* fun startSlotDetailActivity(item: SlotsDetail) {
        startActivity(Intent(this, SlotDetailActivity::class.java).putExtra("slot",item))
>>>>>>> Stashed changes
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }*/
        /* fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }

    fun startAddLeadActivity() {
        startActivityForResult(Intent(this, AddLeadActivity::class.java),1000)
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }
    fun startResetPasswordActivity() {
        startActivity(Intent(this, ResetPassword::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    open fun startCongratulationctivity() {
        startActivity(Intent(this, CongratulationActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }

    fun startHomeActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }

    fun startForgotPasswordActivity() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }

    fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }
    fun startCovidActivity() {
        startActivity(Intent(this, CovidInsuranceActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }
    fun startWebViewActivity(url:String,title:String) {
        startActivity(Intent(this, WebViewActivity::class.java).
        putExtra("url",url).putExtra("title",title))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }
    fun startIHOActivity() {
        startActivity(Intent(this, IHOActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startLoginActivityOnBackPress() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
        finish()
    }

    fun startProfileActivity() {
        var intent = Intent(this, CreateProfileActivity::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        )
        startActivity(intent)
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }
    fun startPaymentActivity() {
        startActivity(Intent(this, PaymentActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }
    fun startLeadsActivity() {
        startActivity(Intent(this, LeadListActivity::class.java))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
        finish()
    }
    fun startLeadDetail(mobile: String) {
        startActivity(Intent(this, LeadProfileActivity::class.java).putExtra("userId",mobile))
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun startPaymentActivity(userId:String,emailId: String,mobile:String,name:String,launchScreen:String?) {
        startActivityForResult(Intent(this, PaymentActivity::class.java)
            .putExtra(this.userId,userId)
            .putExtra(this.emailId,emailId)
            .putExtra(this.mobile,mobile)
            .putExtra(this.name,name)
            .putExtra(this.launchScreen,launchScreen),1200)
        overridePendingTransition(R.anim.anim_right_to_left, R.anim.anim_out_right_to_left)
    }

    fun showProgressBar(message: String, view: View) {
        view.visibility = View.GONE
      *//*  var messageText = view.findViewById<TextView>(R.id.pbText)
        messageText.text = message
 *//*
        progressBar.show(this,"Loading..");

    }

    fun hideProgressBar(view: View) {
        view.visibility = View.GONE
        progressBar.getDialog().dismiss();
    }*/

    }
}
