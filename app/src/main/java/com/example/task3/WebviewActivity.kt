package com.example.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.task3.SharedPreferences.MyPreferences
import com.example.task3.SharedPreferences.MyPreferencesImpl
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    private lateinit var myPreferences: MyPreferences

    private lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val actionBar = supportActionBar
        actionBar?.title = "Web View"

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        myPreferences = MyPreferencesImpl(this)
        url = myPreferences.getString(MyPreferences.WEBURL).toString()

        wv_webview.webViewClient = WebViewClient()
        wv_webview.loadUrl(url)
        val webSettings = wv_webview.settings
        webSettings.javaScriptEnabled = true
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        if (wv_webview!!.canGoBack())
            wv_webview.goBack()
        else
            super.onBackPressed()
    }
}


