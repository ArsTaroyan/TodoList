package am.a_t.todolist.presentation.adapter

import am.a_t.todolist.databinding.ItemTodoBinding
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.presentation.ui.homeFragment.HomeViewModel
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val viewModel: HomeViewModel, private val click: (Todo) -> Unit) :
    ListAdapter<Todo, TodoAdapter.MyViewHolder>(DiffUtilItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTodoBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            with(binding) {
                if (!todo.isChecked) {
                    tvText.text = todo.text
                } else {
                    val spannableString = SpannableString(todo.text)
                    val strikethroughSpan = StrikethroughSpan()
                    spannableString.setSpan(
                        strikethroughSpan,
                        0,
                        todo.text.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    tvText.text = spannableString
                }
                isChecked.isChecked = todo.isChecked
                btnDelete.setOnClickListener {
                    click(todo)
                }
                isChecked.setOnClickListener {
                    viewModel.updateTodo(todo.copy(isChecked = isChecked.isChecked))
                }
            }
        }
    }

    class DiffUtilItemCallBack : DiffUtil.ItemCallback<Todo>() {

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
            oldItem == newItem

    }

}