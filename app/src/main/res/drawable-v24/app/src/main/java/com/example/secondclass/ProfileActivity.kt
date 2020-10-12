package com.example.secondclass

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionbar = supportActionBar
        actionbar?.title = "User Profile"
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        getUserInfo()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun getUserInfo() {

        val bundle = intent.extras
        val name = bundle?.getString("profile_name", "Rahim")
        val email = bundle?.getString("profile_email", "Rahim")
        val age = bundle?.getInt("profile_age", 25)
        val phone = bundle?.getInt("profile_phone", 1632888127)
        val weight = bundle?.getFloat("profile_weight", 35F)

        /*val name = intent.getStringExtra("profile_name")
        val email = intent.getStringExtra("profile_email")
        val age = intent.getStringExtra("profile_age")
        val phone = intent.getStringExtra("profile_phone")
        val weight = intent.getStringExtra("profile_weight")*/

        tv_get_name.text = "Name: $name"
        tv_get_email.text = "Email: $email"
        tv_get_age.text = "Age: $age"
        tv_get_phone.text = "phone: $phone"
        tv_get_bodyweight.text = "Body Weight: $weight"
    }
}
