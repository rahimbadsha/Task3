package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.task3.CustomDialog.MyCustomDialogPass
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_profile.*

//@Suppress("PLUGIN_WARNING")
class ProfileActivity : AppCompatActivity() {

    private lateinit var myPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionBar = supportActionBar
        actionBar?.title = "User Profile"

        profileGlideImage()

        displayUserInfo()

        button_update_pass?.setOnClickListener {
            updateUserPassword()
        }

        button_website?.setOnClickListener {
            openWebsite()
        }

        button_update_edit?.setOnClickListener {
            updateUserProfile()
        }

        button_profile_logout?.setOnClickListener {
            userLogout()
        }

    }

    private fun profileGlideImage() {
        val imageUrl = "https://i.pinimg.com/564x/f8/2f/ba/f82fbac7514f944aacc0257445c1f89e.jpg"

        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(iv_profile_logo)
    }

    private fun userLogout() {

        myPreferences = MyPreferencesImpl(this)
        myPreferences.clearAll()

        intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()

    }

    private fun updateUserProfile() {
        intent = Intent(this, EditprofileActivity::class.java)
        startActivity(intent)
    }

    private fun openWebsite() {
        intent = Intent(this, WebviewActivity::class.java)
        startActivity(intent)
    }

    private fun updateUserPassword() {
        MyCustomDialogPass().show(supportFragmentManager, "MyCustomFragment")
    }

    private fun displayUserInfo() {
        myPreferences = MyPreferencesImpl(this)
        tv_profile_name?.text = myPreferences.getString(MyPreferences.NAME)
        tv_profile_calls?.text = myPreferences.getString(MyPreferences.PHONE)
        tv_profile_email?.text = myPreferences.getString(MyPreferences.EMAIL)
    }
}
