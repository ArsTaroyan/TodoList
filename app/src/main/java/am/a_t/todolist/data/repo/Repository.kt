package am.a_t.todolist.data.repo

import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.domain.entity.Item
import am.a_t.todolist.domain.iteractors.TodoDao
import am.a_t.todolist.domain.iteractors.ItemDao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val itemDao: ItemDao,
    private val todoDao: TodoDao
) {

    fun getAllItem(): Flow<List<Item>> = itemDao.getAllItem()
    fun getAllTodo(id: Long): Flow<List<Todo>> = todoDao.getAllTodo(id)

    suspend fun getItem(itemId: Long): Item {
        return itemDao.getItem(itemId)
    }

    suspend fun insetItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    suspend fun insetTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

}