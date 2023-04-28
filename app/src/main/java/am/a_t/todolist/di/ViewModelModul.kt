package am.a_t.todolist.di

import am.a_t.todolist.presentation.viewModel.MyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MyViewModel(get())
    }
}