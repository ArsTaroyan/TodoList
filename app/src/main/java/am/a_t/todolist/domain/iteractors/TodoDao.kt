package am.a_t.todolist.domain.iteractors

import am.a_t.todolist.domain.entity.Todo
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("SELECT * FROM todos WHERE user_id = :userId")
    fun getAllTodo(userId: Long): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)
}