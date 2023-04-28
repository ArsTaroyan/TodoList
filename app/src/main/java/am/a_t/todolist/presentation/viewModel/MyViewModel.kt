package am.a_t.todolist.presentation.viewModel

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.model.Todo
import am.a_t.todolist.model.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MyViewModel(private val repository: Repository) : ViewModel() {

    val getUser = MutableSharedFlow<User>(1)
    val usersListListLiveData: LiveData<List<User>> = repository.getAllUser
    fun todosListListLiveData(id: Long): LiveData<List<Todo>> = repository.getAllTodo(id)

    fun getUser(id: Long) {
        viewModelScope.launch {
            getUser.emit(repository.getUser(id))
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

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insetTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }
}