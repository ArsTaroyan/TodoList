package am.a_t.todolist.data.source.database

import am.a_t.todolist.domain.iteractors.TodoDao
import am.a_t.todolist.domain.iteractors.UserDao
import am.a_t.todolist.data.source.convert.Converters
import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.domain.entity.User
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        User::class,
        Todo::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun todoDao(): TodoDao
}