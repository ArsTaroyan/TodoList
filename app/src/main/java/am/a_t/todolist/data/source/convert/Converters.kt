package am.a_t.todolist.data.source.convert

import am.a_t.todolist.domain.entity.Todo
import am.a_t.todolist.extension.convertJsonToString
import am.a_t.todolist.extension.convertStringToJson
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<Todo>): String {
        return list.convertJsonToString()
    }

    @TypeConverter
    fun toList(string: String): List<Todo> {
        return string.convertStringToJson()
    }
}