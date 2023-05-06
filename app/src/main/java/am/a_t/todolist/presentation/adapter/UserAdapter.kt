package am.a_t.todolist.presentation.adapter

import am.a_t.todolist.databinding.ItemUserBinding
import am.a_t.todolist.domain.entity.User
import am.a_t.todolist.presentation.viewModel.CreateNewTodoListViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val viewModel: CreateNewTodoListViewModel, private val click: (Long) -> Unit) :
    ListAdapter<User, UserAdapter.MyViewHolder>(DiffUtilItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUserBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            click(getItem(position).id)
        }
    }

    inner class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvUserName.text = user.name
                btnDelete.setOnClickListener {
                    viewModel.deleteUser(user)
                }
            }
        }
    }

    class DiffUtilItemCallBack : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

    }
}