package com.example.task3.CustomDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.task3.R
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_my_custom_dialog.*

class MyCustomDialogPass: DialogFragment() {
    private lateinit var myPreferences: MyPreferences

    private lateinit var dialogPass: String
    private lateinit var dialogRepeatPass: String

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.activity_my_custom_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPreferences = MyPreferencesImpl(view.context)
        val pass = myPreferences.getString(MyPreferences.PASS)
        val repeatPass = myPreferences.getString(MyPreferences.REPEATPASS)

        et_dialog_pass.setText(pass)
        et_dialog_repeatPass.setText(repeatPass)

        button_pass_update.setOnClickListener {
            dialogPass = et_dialog_pass.text.toString().trim()
            dialogRepeatPass = et_dialog_repeatPass.text.toString().trim()

            validatePass()

            onSuccessUpdating()
        }

    }

    private fun onSuccessUpdating() {
        if(validatePass())
        {
            myPreferences.setString(MyPreferences.PASS, dialogPass)
            myPreferences.setString(MyPreferences.REPEATPASS, dialogRepeatPass)
            Toast.makeText(view?.context, "Password Updated!", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
    }

    private fun validatePass(): Boolean{
        if (dialogPass.isEmpty())
        {
            et_dialog_pass.error = "Password field can't be empty"
            et_dialog_pass.requestFocus()
            return false
        }
        if (dialogPass.length < 6)
        {
            et_dialog_pass.error = "Pass must be 6 length or more"
            et_dialog_pass.requestFocus()
            return false
        }
        if (dialogRepeatPass.isEmpty())
        {
            et_dialog_repeatPass.error = "Password repeat field can't be empty"
            et_dialog_repeatPass.requestFocus()
            return false
        }
        if (dialogPass != dialogRepeatPass)
        {
            et_dialog_repeatPass.error = "Password Does not match"
            et_dialog_repeatPass.requestFocus()
            return false
        }

        return true
    }

    override fun onStart() {
        super.onStart()

        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.40).toInt()

        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}