package com.example.secondclass

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_edit.*
import kotlinx.android.synthetic.main.activity_profile_edit.button_login

class EditProfileActivity : AppCompatActivity() {

    private var profileName = ""
    private var profileEmail = ""
    private var profileAge = ""
    private var profilePhone = ""
    private var profileWeight= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        //Actionbar
        val actionbar = supportActionBar
        //set action bar title
        actionbar!!.title = "Edit Profile"
        //Back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        button_login.setOnClickListener {

            saveButton()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveButton() {

        profileName = et_profile_name.text.toString().trim()
        profileEmail = et_profile_email.text.toString().trim()
        profileAge = et_profile_age.text.toString().trim()
        profilePhone = et_profile_phone.text.toString().trim()
        profileWeight = et_profile_bodyweight.text.toString().trim()

        //Check the fields if there is no value
        isValidateProfileEditField()

        if (isValidateProfileEditField())
        {
            activityProfileView()
        }
    }

    private fun activityProfileView() {

        val intent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()
        bundle.putString("profile_name", profileName)
        bundle.putString("profile_email", profileEmail)
        bundle.putString("profile_age", profileAge)
        bundle.putString("profile_phone", profilePhone)
        bundle.putString("profile_weight", profileWeight)
        intent.putExtras(bundle)

        /*
        intent.putExtra("profile_name", profile_name)
        intent.putExtra("profile_email", profile_email)
        intent.putExtra("profile_age", profile_age)
        intent.putExtra("profile_phone", profile_phone)
        intent.putExtra("profile_weight", profile_weight)*/

        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        //finish()
    }

    private fun isValidateProfileEditField():Boolean {

        if (profileName.isEmpty())
        {
            et_profile_name?.error = "Name field can't be empty"
            et_profile_name.requestFocus()
            return false
        }

        if (profileEmail.isEmpty())
        {
            et_profile_email?.error = "Email field can't be empty"
            et_profile_email.requestFocus()
            return false
        }

        if (profileAge.isEmpty())
        {
            et_profile_age?.error = "Age field can't be empty"
            et_profile_age.requestFocus()
            return false
        }

        if (profilePhone.isEmpty())
        {
            et_profile_phone?.error = "Phone field can't be empty"
            et_profile_phone.requestFocus()
            return false
        }

        if (profileWeight.isEmpty())
        {
            et_profile_bodyweight?.error = "Body Weight field can't be empty"
            et_profile_bodyweight.requestFocus()
            return false
        }

        return true
    }

}
