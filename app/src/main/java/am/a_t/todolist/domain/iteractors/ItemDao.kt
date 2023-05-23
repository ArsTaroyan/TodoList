package am.a_t.todolist.domain.iteractors

import am.a_t.todolist.domain.entity.Item
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItem(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :itemId")
    suspend fun getItem(itemId: Long): Item

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}