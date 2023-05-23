package am.a_t.todolist.presentation.ui.newTodoList

import am.a_t.todolist.databinding.FragmentCreateNewTodoListBinding
import am.a_t.todolist.databinding.RemoveDialogBinding
import am.a_t.todolist.databinding.TodoDialogBinding
import am.a_t.todolist.domain.entity.Item
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.presentation.adapter.ItemAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNewTodoListFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewTodoListBinding
    private lateinit var mItemAdapter: ItemAdapter
    private val viewModel by viewModel<CreateNewTodoListViewModel>()
    private lateinit var item: Item

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewTodoListBinding.inflate(inflater, container, false)

        initAdapter(inflater, container)
        initViewModel()
        initClickListeners()

        return binding.root
    }

    private fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        mItemAdapter = ItemAdapter(requireContext(), inflater, container, viewModel) {
            findNavController().navigate(
                CreateNewTodoListFragmentDirections.actionLoginFragmentToHomeFragment(
                    it
                )
            )
        }
        binding.rvUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserList.adapter = mItemAdapter
    }

    private fun initClickListeners() {
        with(binding) {
            btnAdd.setOnClickListener {
                if (edName.text.toString().isNotEmpty()) {
                    item = Item(
                        0,
                        edName.text.toString(),
                        emptyList()
                    )
                    viewModel.addUsers(
                        item
                    )
                    edName.setText("")
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.usersList()
        lifecycleScope.launch {
            viewModel.itemsListLiveData.first().collectLatest {
                mItemAdapter.submitList(it)
            }
        }
    }

}