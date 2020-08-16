package com.example.feeddemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.feeddemo.R
import com.example.feeddemo.databinding.ItemUserBinding
import com.example.feeddemo.model.entity.User


class UserListAdapter(private val newsList: ArrayList<User>, private val callBack: Callback) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user, parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = newsList[position]
        val root = holder.binding
        root.apply {
            tvFullName.text="${data.firstName} ${data.lastName}"
            tvEmail.text=data.emailId
            imgUrl.load(data.imageUrl) {
                crossfade(true)
                error(R.drawable.ic_dummy_image)
            }
        }
    }
    class ViewHolder(internal var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}

interface Callback
