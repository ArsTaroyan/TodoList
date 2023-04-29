package am.a_t.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "todos"
)
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("user_id") val user_id: Long,
    @ColumnInfo("text_todo") val text: String,
    @ColumnInfo("is_checked") var isChecked: Boolean
)
