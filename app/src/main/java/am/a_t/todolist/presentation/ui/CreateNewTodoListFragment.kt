package am.a_t.todolist.presentation.ui

import am.a_t.todolist.databinding.FragmentCreateNewTodoListBinding
import am.a_t.todolist.model.User
import am.a_t.todolist.presentation.adapter.UserAdapter
import am.a_t.todolist.presentation.viewModel.MyViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNewTodoListFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewTodoListBinding
    private lateinit var userAdapter: UserAdapter
    private val viewModel by viewModel<MyViewModel>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewTodoListBinding.inflate(inflater, container, false)

        initAdapter()
        initViewModel()
        initClickListeners()

        return binding.root
    }

    private fun initAdapter() {
        userAdapter = UserAdapter(viewModel) {
            findNavController().navigate(
                CreateNewTodoListFragmentDirections.actionLoginFragmentToHomeFragment(
                    it
                )
            )
        }
        binding.rvUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUserList.adapter = userAdapter
    }

    private fun initClickListeners() {
        with(binding) {
            btnOpen.setOnClickListener {
                if (edName.text.toString().isNotEmpty()) {
                    user = User(
                        0,
                        edName.text.toString(),
                        emptyList()
                    )
                    viewModel.addUsers(
                        user
                    )
                    edName.setText("")
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.usersListListLiveData.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }

}