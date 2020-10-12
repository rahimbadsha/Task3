package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_editprofile.*
import kotlinx.android.synthetic.main.activity_register.*

class EditprofileActivity : AppCompatActivity() {

    private lateinit var myPreference:MyPreferences
    
    private lateinit var editName: String
    private lateinit var editPhone: String
    private lateinit var editEmail: String
    private lateinit var editPass: String
    private lateinit var editRepeatPass: String
    private lateinit var editURL: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        val actionBar = supportActionBar
        actionBar?.title = "Edit Profile"

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        showBeforeText()

        button_edit.setOnClickListener {
            userEdit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showBeforeText() {
        myPreference = MyPreferencesImpl(this)
        val name = myPreference.getString(MyPreferences.NAME)
        val phone = myPreference.getString(MyPreferences.PHONE)
        val email = myPreference.getString(MyPreferences.EMAIL)
        val pass = myPreference.getString(MyPreferences.PASS)
        val repeatPass = myPreference.getString(MyPreferences.REPEATPASS)
        val url = myPreference.getString(MyPreferences.WEBURL)

        et_edit_name.setText(name)
        et_edit_phone.setText(phone)
        et_edit_email.setText(email)
        et_edit_pass.setText(pass)
        et_edit_repeatPass.setText(repeatPass)
        et_edit_webUrl.setText(url)
    }

    private fun userEdit() {

        editName = et_edit_name.text.toString().trim()
        editPhone = et_edit_phone.text.toString().trim()
        editEmail = et_edit_email.text.toString().trim()
        editPass = et_edit_pass.text.toString().trim()
        editRepeatPass = et_edit_repeatPass.text.toString().trim()
        editURL = et_edit_webUrl.text.toString().trim()


        //validate the filed
        isValidateEditField()

        if (isValidateEditField())
        {
            //Save data using sharedpreferences
            myPreference = MyPreferencesImpl(this)
            myPreference.setString(MyPreferences.NAME, editName)
            myPreference.setString(MyPreferences.PHONE, editPhone)
            myPreference.setString(MyPreferences.EMAIL, editEmail)
            myPreference.setString(MyPreferences.PASS, editPass)
            myPreference.setString(MyPreferences.REPEATPASS, editRepeatPass)
            myPreference.setString(MyPreferences.WEBURL, editURL)

            Toast.makeText(this, "Profile Info Updated!", Toast.LENGTH_SHORT).show()
            intent = Intent(this, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun isValidateEditField():Boolean {

        if (editName.isEmpty())
        {
            et_edit_name?.error = "Name Field can't be empty"
            et_edit_name.requestFocus()
            return false
        }

        if (et_edit_phone.length() <= 0)
        {
            et_edit_phone?.error = "Phone field can't be empty"
            et_edit_phone.requestFocus()
            return false
        }
        if (!Patterns.PHONE.matcher(editPhone).matches())
        {
            et_edit_phone?.error = "Phone number not valid"
            et_edit_phone.requestFocus()
            return false
        }

        if(editEmail.isEmpty())
        {
            et_edit_email.error = "Email Field Can't be empty"
            et_edit_email.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(editEmail).matches())
        {
            et_edit_email.error = "Email is invalid"
            et_edit_email.requestFocus()
            return false
        }

        if (editPass.isEmpty())
        {
            et_edit_pass.error = "Password field can't be empty"
            et_edit_pass.requestFocus()
            return false
        }
        if (editPass.length < 6)
        {
            et_edit_pass.error = "Pass must be 6 length or more"
            et_edit_pass.requestFocus()
            return false
        }
        if (editRepeatPass.isEmpty())
        {
            et_register_repeatPass.error = "Password repeat field can't be empty"
            et_register_repeatPass.requestFocus()
            return false
        }
        if (editPass != editRepeatPass)
        {
            et_register_repeatPass.error = "Password Does not match"
            et_register_repeatPass.requestFocus()
            return false
        }

        if (editURL.isEmpty())
        {
            et_edit_webUrl.error = "URL field can't be empty"
            et_edit_webUrl.requestFocus()
            return false
        }
        if (!Patterns.WEB_URL.matcher(editURL).matches())
        {
            et_edit_webUrl.error = "URL not valid"
            et_edit_webUrl.requestFocus()
            return false
        }

        return true
    }
}
