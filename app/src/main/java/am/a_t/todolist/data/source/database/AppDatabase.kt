package am.a_t.todolist.data.source.database

import am.a_t.todolist.data.source.convert.Converters
import am.a_t.todolist.domain.entity.Item
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.domain.iteractors.ItemDao
import am.a_t.todolist.domain.iteractors.TodoDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Item::class,
        Todo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun todoDao(): TodoDao
}