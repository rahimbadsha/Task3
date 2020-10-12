package com.example.secondclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val defaultEmail = "rahim@gmail.com"
    private val defaultPass = "12345r"
    private var loginEmail = ""
    private var loginPass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionbar = supportActionBar
        actionbar?.title = "User Login"

        button_login.setOnClickListener {

            loginButtonAction()
        }
    }

    private fun loginButtonAction() {

       loginEmail = et_login_email.text.toString().trim()
       loginPass = et_login_password.text.toString().trim()

        isValidateLoginField()

        // if valid
        if(isValidateLoginField())
        {
            if (loginEmail == defaultEmail && loginPass == defaultPass)
            {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, EditProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isValidateLoginField():Boolean {

        //validate email filed
        if (loginEmail.isEmpty())
        {
            et_login_email?.error = "Email field can't be empty"
            et_login_email.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches())
        {
            et_login_email?.error = "Enter valid email address"
            et_login_email.requestFocus()
            return false
        }

        //validate pass filed
        if (loginPass.isEmpty())
        {
            et_login_password?.error = "Password field can't be empty"
            et_login_password.requestFocus()
            return false
        }

        if (loginPass.length < 6)
        {
            et_login_password?.error = "Pass must be 6 length"
            et_login_password.requestFocus()
            return false
        }

        if(loginEmail != defaultEmail || loginPass != defaultPass)
        {
            et_login_email?.error = "Email or password does not match"
            et_login_email.requestFocus()

            et_login_password?.error = "Email or password does not match"
            et_login_password.requestFocus()

            return false
        }

        return true
    }
}
