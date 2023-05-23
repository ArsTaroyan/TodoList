package am.a_t.todolist.presentation.ui.newTodoList

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.domain.entity.Item
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CreateNewTodoListViewModel(private val repository: Repository) : ViewModel() {
    val itemsListLiveData = MutableSharedFlow<Flow<List<Item>>>(1)

    fun usersList() {
        viewModelScope.launch {
            itemsListLiveData.emit(repository.getAllItem())
        }
    }

    fun addUsers(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insetItem(item)
        }
    }

    fun deleteUser(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }
}