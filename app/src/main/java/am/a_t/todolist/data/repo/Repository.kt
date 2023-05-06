package am.a_t.todolist.data.repo

import am.a_t.todolist.domain.iteractors.TodoDao
import am.a_t.todolist.domain.iteractors.UserDao
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.domain.entity.User
import kotlinx.coroutines.flow.Flow

class Repository(
    private val userDao: UserDao,
    private val todoDao: TodoDao
) {

    fun getAllUser(): Flow<List<User>> = userDao.getAllUser()
    fun getAllTodo(id: Long): Flow<List<Todo>> = todoDao.getAllTodo(id)

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