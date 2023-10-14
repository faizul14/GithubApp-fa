package com.faezolfp.githubapp.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.databinding.ItemUserFavoriteBinding
import com.faezolfp.githubapp.presentation.detail.DetailActivity

class FavoriteActivityAdapter() : ListAdapter<ModelDataUser, FavoriteActivityAdapter.ViewHolder>(
    DIFF_CALLBACK()) {
    private val listData = ArrayList<ModelDataUser>()

    fun setData(newData: List<ModelDataUser>) {
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemUserFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataUser: ModelDataUser) {
            Glide.with(itemView.context)
                .load(dataUser.avatarUrl)
                .into(binding.imgUser)
            binding.nameUser.text = dataUser.login
            itemView.setOnClickListener {
                val move = Intent(itemView.context, DetailActivity::class.java)
                move.putExtra(DetailActivity.EXTRA_NAME, dataUser.login)
                itemView.context.startActivity(move)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemUserFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(listData[position])
        holder.bind(getItem(position))
    }

//    override fun getItemCount(): Int = listData.size

    class DIFF_CALLBACK : DiffUtil.ItemCallback<ModelDataUser>() {
        override fun areItemsTheSame(oldItem: ModelDataUser, newItem: ModelDataUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ModelDataUser, newItem: ModelDataUser): Boolean {
            return oldItem.login == newItem.login
        }
    }
}