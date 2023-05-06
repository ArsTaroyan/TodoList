package am.a_t.todolist.di

import am.a_t.todolist.presentation.viewModel.CreateNewTodoListViewModel
import am.a_t.todolist.presentation.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CreateNewTodoListViewModel(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}