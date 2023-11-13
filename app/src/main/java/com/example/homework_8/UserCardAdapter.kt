package com.example.homework_8

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_8.databinding.UserCardBinding

class UserCardAdapter() : RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder>() {

    private var userList: MutableList<User> = mutableListOf()

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
    }

    fun addUser(){ // add User with button
        val newUser = User("-", "-", "-")
        userList.add(newUser)
        notifyItemChanged(userList.size)
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
    }

}