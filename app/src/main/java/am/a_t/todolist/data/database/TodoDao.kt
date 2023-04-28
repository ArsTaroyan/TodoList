package am.a_t.todolist.data.database

import am.a_t.todolist.model.Todo
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodoDao {

    @Query("SELECT * FROM todos WHERE user_id = :userId")
    fun getAllTodo(userId: Long): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)
}