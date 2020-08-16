package com.example.feeddemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.feeddemo.R
import com.example.feeddemo.ShowToast
import com.example.feeddemo.databinding.ActivityMainBinding
import com.example.feeddemo.model.entity.User
import com.example.feeddemo.pojo.RequestModel
import com.example.feeddemo.pojo.ViewState
import com.example.feeddemo.ui.adapter.Callback
import com.example.feeddemo.ui.adapter.UserListAdapter
import com.example.feeddemo.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import www.sanju.motiontoast.MotionToast

class MainActivity : AppCompatActivity(),
    Callback {
    private val userViewModel by viewModel<UserViewModel>()
    private val requestModel by lazy { RequestModel(emailId = resources.getString(R.string.dummy_email)) }
    private val binding by lazy { DataBindingUtil.setContentView(this,
        R.layout.activity_main
    ) as ActivityMainBinding }
    private var listData : ArrayList<User> = ArrayList()
    private val userAdapter by lazy {
        UserListAdapter(
            listData,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.rvData.adapter= userAdapter
        userViewModel.fetchUserList(requestModel)
         userViewModel.dataObserve.observe(this, Observer {response->
             when(response){
                 is ViewState.Success ->{
                         response.data.apply {
                             this.observe(this@MainActivity, Observer {
                                 listData.clear()
                                 listData.addAll(it as ArrayList<User>)
                                 userAdapter.notifyDataSetChanged()
                             })
                         }

                     ShowToast(
                         this,
                         resources.getString(R.string.sucess_message),
                         MotionToast.TOAST_SUCCESS
                     )
                 } is ViewState.Loading ->{
                 Log.e("status","loading")
                 } is ViewState.Error ->{
                 ShowToast(
                     this,
                     resources.getString(R.string.error_message),
                     MotionToast.TOAST_ERROR
                 )
                 }is ViewState.NetworkError ->{
                 ShowToast(
                     this,
                     resources.getString(R.string.network_error_message),
                     MotionToast.TOAST_NO_INTERNET
                 )
                 }
             }
         })
    }
}