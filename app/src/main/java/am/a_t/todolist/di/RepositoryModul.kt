package am.a_t.todolist.di

import am.a_t.todolist.data.repo.Repository
import am.a_t.todolist.data.source.database.AppDatabase
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        Repository(getDatabase(androidContext()).itemDao(), getDatabase(androidContext()).todoDao())
    }
}

@Volatile
private var INSTANCE: AppDatabase? = null
fun getDatabase(context: Context): AppDatabase {
    val tempInstance = INSTANCE
    if (tempInstance != null) {
        return tempInstance
    }
    synchronized(context.applicationContext) {
        val instance = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "user_database"
        ).build()
        INSTANCE = instance
        return instance
    }
}