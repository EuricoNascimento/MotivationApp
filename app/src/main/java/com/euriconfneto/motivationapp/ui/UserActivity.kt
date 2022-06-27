package com.euriconfneto.motivationapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.euriconfneto.motivationapp.databinding.ActivityUserBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.euriconfneto.motivationapp.infra.MotivationConstants
import com.euriconfneto.motivationapp.R
import com.euriconfneto.motivationapp.infra.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder action bar
        supportActionBar?.hide()

        //Eventos
        binding.buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                R.string.validation_mandatory_name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}