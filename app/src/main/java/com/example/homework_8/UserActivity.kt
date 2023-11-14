package com.example.homework_8

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_8.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var user: User
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
        updateUser()
    }

    private fun setUp(){
        user = if(Build.VERSION.SDK_INT > 33) {
            intent.getParcelableExtra("data", User::class.java) ?: User("jj", "", "")
        } else{
            intent.getParcelableExtra<User>("data") ?: User("jj", "", "")
        }
        index = intent.getIntExtra("index", 0)
        fieldMapping()
    }

    private fun updateUser(){
        binding.bUpdate.setOnClickListener {
            val i = intent
            userMapping()
            i.putExtra("data", user)
            i.putExtra("index", index)
            setResult(RESULT_OK, i)
            finish()
        }
    }

    private fun userMapping(){
        binding.apply {
            user.firstName = etFirstName.text.toString()
            user.lastName = etLastName.text.toString()
            user.email = etEmail.text.toString()
        }
    }

    private fun fieldMapping(){
        binding.apply {
            etFirstName.setText(user.firstName)
            etLastName.setText(user.lastName)
            etEmail.setText(user.email)
        }
    }
}


