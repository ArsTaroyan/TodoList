package am.a_t.todolist.model

import androidx.room.*

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