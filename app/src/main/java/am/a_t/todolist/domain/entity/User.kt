package am.a_t.todolist.domain.entity

import am.a_t.todolist.domain.entity.Todo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index("name", unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val name: String,
    @ColumnInfo(name = "todo_list") val todoList: List<Todo>
)