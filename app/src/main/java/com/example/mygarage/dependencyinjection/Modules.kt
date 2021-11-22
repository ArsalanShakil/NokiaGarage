package com.example.mygarage.dependencyinjection

import com.example.mygarage.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

private val appModules : Module = module {
}
private val viewModelModule: Module = module {
    viewModel { HomeViewModel() }
}

val modules =
    listOf(appModules, viewModelModule)
