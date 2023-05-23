package am.a_t.todolist.presentation.ui.homeFragment

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.domain.entity.Item
import am.a_t.todolist.domain.entity.Todo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val getItem = MutableSharedFlow<Item>(1)
    val todosListLiveData = MutableSharedFlow<Flow<List<Todo>>>(1)

    fun todosList(id: Long) {
        viewModelScope.launch {
            todosListLiveData.emit(repository.getAllTodo(id))
        }
    }

    fun getUser(id: Long) {
        viewModelScope.launch {
            getItem.emit(repository.getItem(id))
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