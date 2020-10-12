package com.example.task3.SharedPreferences

import android.content.Context

class MyPreferencesImpl(context: Context?): MyPreferences {

    private val sharedPreferences = context?.getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)
    private val editor = sharedPreferences?.edit()
    //private val editor = SharedPreferences.Editor()

    override fun getString(key: String): String? {
        return sharedPreferences?.getString(key, "")
    }

    override fun setString(key: String, value: String) {
        editor?.putString(key, value)
        editor?.apply()
    }

    override fun getInt(key: String): Int? {
        return sharedPreferences?.getInt(key, -1)
    }

    override fun setInt(key: String, value: Int) {
        editor?.putInt(key, value)
        editor?.apply()
    }

    override fun getFloat(key: String): Float? {
        return sharedPreferences?.getFloat(key, -1F)
    }

    override fun setFloat(key: String, value: Float) {
        editor?.putFloat(key, value)
        editor?.apply()
    }

    override fun getBoolean(key: String): Boolean? {
        return sharedPreferences?.getBoolean(key, false)
    }

    override fun getBoolean(key: String, value: Boolean) {
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    override fun clearAll()
    {
        editor?.clear()
        editor?.commit()
    }
}