package am.a_t.todolist.presentation

import am.a_t.todolist.di.repositoryModule
import am.a_t.todolist.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationModule : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(repositoryModule, viewModelModule)
        }
    }

}