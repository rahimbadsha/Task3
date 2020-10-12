package com.example.secondclass

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_input_part.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_submit.setOnClickListener {

           submitButtonAction()
        }
    }

    private fun submitButtonAction() {

        group_input.visibility = View.GONE

        val name = et_name.text.toString()

        tv_output.text = name

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}
