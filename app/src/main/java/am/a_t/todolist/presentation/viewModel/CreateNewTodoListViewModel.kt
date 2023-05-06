package am.a_t.todolist.presentation.viewModel

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.domain.entity.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CreateNewTodoListViewModel(private val repository: Repository) : ViewModel() {
    val usersListLiveData = MutableSharedFlow<Flow<List<User>>>(1)

    fun usersList() {
        viewModelScope.launch {
            usersListLiveData.emit(repository.getAllUser())
        }
    }

    fun addUsers(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insetUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
}