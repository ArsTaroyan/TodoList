package am.a_t.todolist.presentation.viewModel

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.domain.entity.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val getUser = MutableSharedFlow<User>(1)
    val todosListLiveData = MutableSharedFlow<Flow<List<Todo>>>(1)

    fun todosList(id: Long) {
        viewModelScope.launch {
            todosListLiveData.emit(repository.getAllTodo(id))
        }
    }

    fun getUser(id: Long) {
        viewModelScope.launch {
            getUser.emit(repository.getUser(id))
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