package com.example.homework_8

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_8.databinding.ActivityMainBinding

class UsersActivity : AppCompatActivity(), RecyclerViewInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserCardAdapter
    private lateinit var userList: MutableList<User>

    private val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val index = it.data?.extras?.getInt("index") ?: 0
            if(Build.VERSION.SDK_INT > 33) {
                it.data?.extras?.getParcelable("omo", User::class.java)?.let { user ->
                    updateUser(user, index)
                }
            }
            else{
                it.data?.extras?.getParcelable<User>("data")?.let { user ->
                    updateUser(user, index)
                }
            }
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList = mutableListOf()
        setUp()
        addUser()
    }

    private fun setUp(){
        adapter = UserCardAdapter(userList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun addUser(){
        binding.bAdd.setOnClickListener {
            adapter.addUser()
        }
    }

    private fun updateUser(user: User, index: Int){
        adapter.updateUserInfo(user, index)
    }

    override fun onClickPos(pos: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("data", userList[pos])
        intent.putExtra("index", pos)
        result.launch(intent)
    }

}