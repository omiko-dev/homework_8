package com.example.homework_8

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_8.databinding.UserCardBinding

class UserCardAdapter(
    private var userList: MutableList<User>,
    private val recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder {
        return UserCardViewHolder(
            UserCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {
        holder.bind()
        holder.deleteUser()
        holder.updateUser()
    }

    fun addUser(){ // add User with button
        val newUser = User("", "", "")
        userList.add(newUser)
        notifyItemChanged(userList.size)
    }

    fun updateUserInfo(user: User, index: Int){
        userList[index] = user
        notifyItemChanged(index)
    }

    inner class UserCardViewHolder(private val binding: UserCardBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            val user = userList[adapterPosition]
            binding.apply {
                email.text = user.email
                firstName.text = user.firstName
                lastName.text = user.lastName
            }
        }

        fun deleteUser(){ // delete User with index <-> (adapterPosition)
            binding.bRemove.setOnClickListener {
                userList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

        fun updateUser(){
            binding.bUpdate.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    recyclerViewInterface.onClickPos(pos)
                }
            }
        }
    }

}
