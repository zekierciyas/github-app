package com.zekierciyas.github_app.feat_userlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zekierciyas.github_app.R
import com.zekierciyas.github_app.databinding.ContentUserListBinding
import com.zekierciyas.github_app.feat_userlist.domain.model.UserListDomainModel

class UserListAdapter(
    private val itemClicked: (String) -> Unit,
    private val favoriteButtonClicked: (String) -> Unit
) : ListAdapter<UserListDomainModel, UserListAdapter.UserViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContentUserListBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }


    inner class UserViewHolder(private val binding: ContentUserListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener{
                itemClicked.invoke(binding.name.text.toString())
            }
            binding.favoriteButton.setOnClickListener{
                favoriteButtonClicked.invoke(binding.name.text.toString())
            }
        }

        fun bind(user: UserListDomainModel) {
            //Binding the given domain model to content
            binding.userId.text = user.id.toString()
            binding.name.text = user.login.toString()
            binding.imageProfile.loadImage(user.avatarUrl)
            binding.type.text = user.type
            binding.favoriteButton.isSelected = user.isFavorite
            binding.favoriteButton.isChecked = user.isFavorite
            binding.favoriteButton.tag = adapterPosition
        }
    }
}

class UserListDiffCallback : DiffUtil.ItemCallback<UserListDomainModel>() {
    override fun areItemsTheSame(oldItem: UserListDomainModel, newItem: UserListDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserListDomainModel, newItem: UserListDomainModel): Boolean {
        return oldItem == newItem
    }
}
