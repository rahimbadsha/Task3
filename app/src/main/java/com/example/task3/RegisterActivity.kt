package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.core.app.TaskStackBuilder
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var myPreference:MyPreferences

    private lateinit var registerName: String
    private lateinit var registerPhone: String
    private lateinit var registerEmail: String
    private lateinit var registerPass: String
    private lateinit var registerRepeatPass: String
    private lateinit var registerURL: String
    /*private var registerName = ""
    private var registerPhone = ""
    private var registerEmail = ""
    private var registerPass = ""
    private var registerRepeatPass = ""
    private var registerURL = ""*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionbar = supportActionBar
        actionbar?.title = "User Registration"

        //Back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)


        button_register.setOnClickListener {
            userRegister()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun userRegister() {
        registerName = et_register_name.text.toString().trim()
        registerPhone = et_register_phone.text.toString().trim()
        registerEmail = et_register_email.text.toString().trim()
        registerPass = et_register_pass.text.toString().trim()
        registerRepeatPass = et_register_repeatPass.text.toString().trim()
        registerURL = et_register_webUrl.text.toString().trim()

        //validate the filed
        isValidateRegisterField()

        if (isValidateRegisterField())
        {
            //Save data using sharedpreferences
            myPreference = MyPreferencesImpl(this)
            myPreference.setString(MyPreferences.NAME, registerName)
            myPreference.setString(MyPreferences.PHONE, registerPhone)
            myPreference.setString(MyPreferences.EMAIL, registerEmail)
            myPreference.setString(MyPreferences.PASS, registerPass)
            myPreference.setString(MyPreferences.REPEATPASS, registerRepeatPass)
            myPreference.setString(MyPreferences.WEBURL, registerURL)

            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidateRegisterField():Boolean {

        if (registerName.isEmpty())
        {
            et_register_name?.error = "Name Field can't be empty"
            et_register_name.requestFocus()
            return false
        }

        if (et_register_phone.length() <= 0)
        {
            et_register_phone?.error = "Phone field can't be empty"
            et_register_phone.requestFocus()
            return false
        }
        if (!Patterns.PHONE.matcher(registerPhone).matches())
        {
            et_register_phone?.error = "Phone number not valid"
            et_register_phone.requestFocus()
            return false
        }

        if(registerEmail.isEmpty())
        {
            et_register_email.error = "Email Field Can't be empty"
            et_register_email.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches())
        {
            et_register_email.error = "Email is invalid"
            et_register_email.requestFocus()
            return false
        }

        if (registerPass.isEmpty())
        {
            et_register_pass.error = "Password field can't be empty"
            et_register_pass.requestFocus()
            return false
        }
        if (registerPass.length < 6)
        {
            et_register_pass.error = "Pass must be 6 length or more"
            et_register_pass.requestFocus()
            return false
        }
        if (registerRepeatPass.isEmpty())
        {
            et_register_repeatPass.error = "Password repeat field can't be empty"
            et_register_repeatPass.requestFocus()
            return false
        }
        if (registerPass != registerRepeatPass)
        {
            et_register_repeatPass.error = "Password Does not match"
            et_register_repeatPass.requestFocus()
            return false
        }

        if (registerURL.isEmpty())
        {
            et_register_repeatPass.error = "URL field can't be empty"
            et_register_repeatPass.requestFocus()
            return false
        }
        if (!Patterns.WEB_URL.matcher(registerURL).matches())
        {
            et_register_webUrl.error = "URL not valid"
            et_register_webUrl.requestFocus()
            return false
        }

        return true
    }
}
