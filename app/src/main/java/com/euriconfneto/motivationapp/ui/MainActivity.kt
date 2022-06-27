package com.euriconfneto.motivationapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.euriconfneto.motivationapp.R
import com.euriconfneto.motivationapp.data.Mock
import com.euriconfneto.motivationapp.infra.SecurityPreferences
import com.euriconfneto.motivationapp.databinding.ActivityMainBinding
import com.euriconfneto.motivationapp.infra.MotivationConstants

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder
        supportActionBar?.hide()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        //Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

        //Verificação
        handleUserName()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, ${name}!"
    }

    private fun handleFilter(id: Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }
}