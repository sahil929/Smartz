package com.example.smartz.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartz.R
import com.example.smartz.Sensor
import com.example.smartz.base.BaseActivity
import com.example.smartz.databinding.ActivityHomeBinding
import com.example.smartz.model.Body
import com.example.smartz.model.SlotDetail
import com.example.smartz.network.NetworkResponse
import com.example.smartz.network.NetworkResponseType
import com.example.smartz.network.ResponseStatus
import com.example.smartz.utils.Prefrences
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var viewDataBinding: ActivityHomeBinding? = null
    var viewModel: HomeVM?=null
    var list: ArrayList<String>? = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
         viewModel = ViewModelProvider(this).get(HomeVM::class.java)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.homeViewModel = viewModel
        getSlots()
        viewModel?.moveToSlotsDetail?.observe(this, Observer {
           // startSlotDetailActivity(it)
        })
        var toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar as Toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.getDrawerArrowDrawable().setColor(
            ContextCompat.getColor(this, R.color.color_ffffff)
        );

        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)
        navigation.getHeaderView(0).tvMobile.text = Prefrences.getPrefernceInstance().getUserId(this).toString()
        fetchDataFromFirebase()
    }
    fun getSlots(){
        viewModel?.getSlots()?.observe(this, Observer {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    progressBarlist.visibility= View.GONE
                    setAdapter(it.data)
                }
                ResponseStatus.ERROR -> {
                    progressBarlist.visibility= View.GONE
                }
                ResponseStatus.LOADING -> {
                    progressBarlist.visibility= View.VISIBLE}
            }

            if(it.status.equals(ResponseStatus.SUCCESS))
                setAdapter(it.data)
        })
    }

    private fun setAdapter(data: SlotDetail?) {
        viewDataBinding!!.rlSlots?.apply {
         val  layoutManager = LinearLayoutManager(this@HomeActivity)
           // addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
            val adapter = SlotListAdapter(this@HomeActivity,data!!)
            viewDataBinding!!.rlSlots.layoutManager = layoutManager
            viewDataBinding!!.rlSlots.adapter = adapter
        }
    }
    public fun startBookingDetailActivity(body: Body){
        startSlotDetailActivity(body)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home->{
                drawerLayout.closeDrawers()
            }
           R.id.myBooking->{
               startMyBookingActivity()
               drawerLayout.closeDrawers()
           }
          R.id.logout->{
              Prefrences.getPrefernceInstance().clearDatabase(this)
              drawerLayout.closeDrawers()
              finish()
          }
        }
       return true
    }
    public fun fechDataFromFirebase(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()

        // myRef.setValue("Hello, World!")
        // Read from the database

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(String::class.java)!!
                var data:HashMap<String, Sensor> = dataSnapshot.child("sensors").getValue() as HashMap<String, Sensor>
                //var ess = data["13"].toString()
                // var json:JSONObject = JSONObject(ess)


                Log.e("Data", ""+data.keys+" "+data)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("Data", "Failed to read value.", error.toException())
            }
        })
    }
    fun fetchDataFromFirebase(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()

        // myRef.setValue("Hello, World!")
        // Read from the database

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(String::class.java)!!
               // var data:ArrayList<Sensor?> = dataSnapshot.child("slots").getValue() as ArrayList<Sensor?>
                val moshi = Moshi.Builder().build()
                val type: Type = Types.newParameterizedType(
                    List::class.java,
                    Sensor::class.java)
                val adapter: JsonAdapter<List<Sensor>> = moshi.adapter(type)
              //  var data:List<Sensor>? =adapter.fromJson()
                 var jsonArray = JSONArray(dataSnapshot.child("slots").getValue().toString())
                var newData = ArrayList<Sensor>()
                for(x in 0..jsonArray.length()-1){
                 Log.e("testtt",""+x)
                     var jsonObject = jsonArray.get(x)
                     if(jsonObject!= null){
                       var json = jsonObject as JSONObject
                       var sensor = Sensor(jsonObject.getString("slotId").toInt(),jsonObject.getInt(
                           "slotStatus"),jsonObject.getString("startDate"),
                          jsonObject.getInt("timestamp"))
                       newData.add(sensor)
                     }
                 }
                viewModel?.updateSlots(newData)?.observe(this@HomeActivity,
                    Observer<NetworkResponseType<NetworkResponse>> {
                        when(it.status){
                            ResponseStatus.SUCCESS->{
                                getSlots()
                            }
                            ResponseStatus.ERROR->{

                            }
                            ResponseStatus.LOADING->{
                            }
                        }

                    })
    }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("Data", "Failed to read value.", error.toException())
            }
        })

    }
}