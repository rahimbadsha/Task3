package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl

class SplashActivity : AppCompatActivity() {

    private lateinit var myPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        isUserLogin()
    }

    private fun isUserLogin() {
        myPreferences = MyPreferencesImpl(this)
        val email = myPreferences.getString(MyPreferences.EMAIL)
        val pass = myPreferences.getString(MyPreferences.PASS)

        if (email!!.isEmpty() || pass!!.isEmpty())
        {
            intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        else{
            intent = Intent(this, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

}
