package am.a_t.todolist.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "items",
    indices = [
        Index("title", unique = true)
    ]
)
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val title: String,
    @ColumnInfo(name = "todo_list") val todoList: List<Todo>
)