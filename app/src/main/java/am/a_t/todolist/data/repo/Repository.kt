package am.a_t.todolist.data.repo

import am.a_t.todolist.data.database.TodoDao
import am.a_t.todolist.data.database.UserDao
import am.a_t.todolist.model.Todo
import am.a_t.todolist.model.User
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val userDao: UserDao,
    private val todoDao: TodoDao
) {

    val getAllUser: LiveData<List<User>> = userDao.getAllUser()
    fun getAllTodo(id: Long): LiveData<List<Todo>> = todoDao.getAllTodo(id)

    suspend fun getUser(userId: Long): User {
        return userDao.getUser(userId)
    }

    suspend fun insetUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
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