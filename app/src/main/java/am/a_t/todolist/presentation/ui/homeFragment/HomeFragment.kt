package am.a_t.todolist.presentation.ui.homeFragment

import am.a_t.todolist.R
import am.a_t.todolist.databinding.FragmentHomeBinding
import am.a_t.todolist.databinding.RemoveDialogBinding
import am.a_t.todolist.databinding.TodoDialogBinding
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.presentation.adapter.TodoAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var myDialog: TodoDialogBinding
    private lateinit var myDialogRemove: RemoveDialogBinding
    private lateinit var alertDialog: AlertDialog
    private val viewModel by viewModel<HomeViewModel>()
    private val args by navArgs<HomeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initAdapter(inflater, container)
        initViewModel()
        initClickListeners(inflater, container)

        return binding.root
    }

    private fun initDialog(inflater: LayoutInflater, container: ViewGroup?) {
        myDialog = TodoDialogBinding.inflate(inflater, container, false)
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(myDialog.root)
            .show()

        myDialog.btnAddTodo.setOnClickListener {
            if (!myDialog.edText.text.toString().isNullOrEmpty()) {
                viewModel.addTodo(
                    Todo(
                        0,
                        args.userId,
                        myDialog.edText.text.toString(),
                        false
                    )
                )
            }
            alertDialog.dismiss()
        }

        myDialog.btnCloseTodo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun removeDialog(inflater: LayoutInflater, container: ViewGroup?, item: Todo) {
        myDialogRemove = RemoveDialogBinding.inflate(inflater, container, false)
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(myDialogRemove.root)
            .show()

        myDialogRemove.btnYesTodo.setOnClickListener {
            viewModel.deleteTodo(item)
            alertDialog.dismiss()
        }

        myDialogRemove.btnNoTodo.setOnClickListener {
            alertDialog.dismiss()
        }
        
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        todoAdapter = TodoAdapter(viewModel) {
            removeDialog(inflater, container, it)
        }
        binding.rvTodoList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodoList.adapter = todoAdapter
    }

    private fun initViewModel() {
        viewModel.getUser(args.userId)
        viewModel.todosList(args.userId)

        lifecycleScope.launch {
            viewModel.todosListLiveData.first().collectLatest {
                todoAdapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            viewModel.getItem.collect {
                binding.toolBar.title = it.title
            }
        }
    }

    private fun initClickListeners(inflater: LayoutInflater, container: ViewGroup?) {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.btnAdd.setOnClickListener {
            initDialog(inflater, container)
        }
    }

}