package com.example .task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

@Suppress("PLUGIN_WARNING")
class LoginActivity : AppCompatActivity() {

    private lateinit var myPreferences: MyPreferences

    private lateinit var userEmail: String
    private lateinit var userPass: String

    private lateinit var userEmailCheck: String
    private lateinit var userPassCheck: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar = supportActionBar
        actionBar?.title = "User Login"

        saveUserLogin()

        button_login.setOnClickListener {
            validateUserLogin()
        }

        tv_register.setOnClickListener{

            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserLogin() {
        myPreferences = MyPreferencesImpl(this)
        val email = myPreferences.getString(MyPreferences.EMAIL)
        val pass = myPreferences.getString(MyPreferences.PASS)

        et_login_email.setText(email)
        et_login_password.setText(pass)
    }

    private fun validateUserLogin() {

        userEmail = et_login_email.text.toString().trim()
        userPass = et_login_password.text.toString().trim()

        isUserValid()

        if (isUserValid())
        {
            myPreferences = MyPreferencesImpl(this)
            userEmailCheck = myPreferences.getString(MyPreferences.EMAIL).toString()
            userPassCheck = myPreferences.getString(MyPreferences.PASS).toString()

            if (userEmail != userEmailCheck && userPass != userPassCheck)
            {
                Toast.makeText(this, "Email or password does not match", Toast.LENGTH_SHORT).show()
            }
            else
            {
                intent = Intent(this, ProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isUserValid() : Boolean {
        if(userEmail.isEmpty())
        {
            et_register_email.error = "Email Field Can't be empty"
            et_register_email.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            et_register_email.error = "Email is invalid"
            et_register_email.requestFocus()
            return false
        }

        if (userEmail.isEmpty())
        {
            et_register_pass.error = "Password field can't be empty"
            et_register_pass.requestFocus()
            return false
        }
        if (userPass.length < 6)
        {
            et_register_pass.error = "Pass must be 6 length or more"
            et_register_pass.requestFocus()
            return false
        }
        if (userPass.isEmpty())
        {
            et_register_repeatPass.error = "Password repeat field can't be empty"
            et_register_repeatPass.requestFocus()
            return false
        }

        return true
    }
}
