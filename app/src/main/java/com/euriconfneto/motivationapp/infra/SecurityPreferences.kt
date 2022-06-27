package com.euriconfneto.motivationapp.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences (context: Context) {
    private val secure: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, str: String){
        secure.edit().putString(key, str).apply()
    }

    fun getString(key: String): String{
       return secure.getString(key, "") ?: ""
    }
}