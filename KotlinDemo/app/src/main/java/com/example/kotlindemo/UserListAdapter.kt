package com.example.kotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class UserListAdapter(val userList: List<User>): Adapter<UserListAdapter.UserViewHolder>() {

    class UserViewHolder(parentView: View): ViewHolder(parentView) {
         val userName: TextView = parentView.findViewById(R.id.userNameTxtId)
         val email: TextView = parentView.findViewById(R.id.emaiTxtId)
         val address: TextView = parentView.findViewById(R.id.addressTxvId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user: User = userList.get(position)
        holder.userName.text = "Name: " + user.name
        holder.email.text = "Email: " + user.email
        holder.address.text = "Address: " + user.address
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}