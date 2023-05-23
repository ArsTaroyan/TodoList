package am.a_t.todolist.presentation.adapter

import am.a_t.todolist.databinding.ItemItemBinding
import am.a_t.todolist.databinding.RemoveDialogBinding
import am.a_t.todolist.databinding.TodoDialogBinding
import am.a_t.todolist.domain.entity.Item
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.presentation.ui.newTodoList.CreateNewTodoListViewModel
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private var context: Context,
    private var inflater: LayoutInflater,
    private var container: ViewGroup?,
    private val viewModel: CreateNewTodoListViewModel,
    private val click: (Long) -> Unit
) :
    ListAdapter<Item, ItemAdapter.MyViewHolder>(DiffUtilItemCallBack()) {

    private lateinit var myDialog: TodoDialogBinding
    private lateinit var myDialogRemove: RemoveDialogBinding
    private lateinit var alertDialog: AlertDialog

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemItemBinding
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

    inner class MyViewHolder(val binding: ItemItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                tvUserName.text = item.title
                btnDelete.setOnClickListener {
                    removeDialog(inflater, container, item)
                }
            }
        }
    }

    class DiffUtilItemCallBack : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem

    }

    private fun removeDialog(inflater: LayoutInflater, container: ViewGroup?, item: Item) {
        myDialogRemove = RemoveDialogBinding.inflate(inflater, container, false)
        alertDialog = AlertDialog.Builder(context)
            .setView(myDialogRemove.root)
            .show()

        myDialogRemove.btnYesTodo.setOnClickListener {
            viewModel.deleteUser(item)
            alertDialog.dismiss()
        }

        myDialogRemove.btnNoTodo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}