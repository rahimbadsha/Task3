package com.example.task3.SharedPreferences

interface MyPreferences {

    companion object
    {
        const val NAME = "name"
        const val PHONE = "phone"
        const val EMAIL = "email"
        const val PASS = "password"
        const val REPEATPASS = "repeat_pass"
        const val WEBURL = "web_url"
    }

    fun getString(key: String): String?
    fun setString(key: String, value: String)
    fun getInt(key: String): Int?
    fun setInt(Key: String, value: Int)
    fun getFloat(key: String): Float?
    fun setFloat(key: String, value: Float)
    fun getBoolean(key: String): Boolean?
    fun getBoolean(key: String, value: Boolean)
    fun clearAll()
}