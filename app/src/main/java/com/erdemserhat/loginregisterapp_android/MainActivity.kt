package com.erdemserhat.loginregisterapp_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginFragment:Login = Login()
        val fragmentManager:FragmentManager=supportFragmentManager

        val transaction:FragmentTransaction =fragmentManager.beginTransaction()
        transaction.add(R.id.mainFrame,loginFragment,"Login")
        transaction.commit()
    }
}