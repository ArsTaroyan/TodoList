package am.a_t.todolist.di

import am.a_t.todolist.presentation.ui.newTodoList.CreateNewTodoListViewModel
import am.a_t.todolist.presentation.ui.homeFragment.HomeViewModel
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